#set($d = '$')
package util

import kotlinx.browser.window
import kotlinx.coroutines.await
import org.w3c.fetch.*
import kotlin.js.json
import kotlin.random.Random

object RequestUtil {

    suspend fun <T> requestAndParseNullableResult(
        url: String,
        method: String = "GET",
        headers: dynamic = json(),
        requestCredentials: RequestCredentials = RequestCredentials.SAME_ORIGIN,
        requestRedirect: RequestRedirect = RequestRedirect.FOLLOW,
        body: dynamic = null,
        parse: (dynamic) -> T
    ): T? =
        requestAndHandle(
            url = url,
            method = method,
            headers = headers,
            requestCredentials = requestCredentials,
            requestRedirect = requestRedirect,
            body = body,
            handler = { response ->
                when {
                    response.status.toInt() < 400 -> parse(response.json().await())
                    response.status.toInt() == 404 -> null
                    else -> throw BadStatusCodeException(response.status.toInt(), response.getProblemDescription())
                }
            }
        )

    suspend fun <T> requestAndParseResult(
        url: String,
        method: String = "GET",
        headers: dynamic = json(),
        requestCredentials: RequestCredentials = RequestCredentials.SAME_ORIGIN,
        requestRedirect: RequestRedirect = RequestRedirect.FOLLOW,
        body: dynamic = null,
        parse: (dynamic) -> T
    ): T =
        requestAndHandle(
            url = url,
            method = method,
            headers = headers,
            requestCredentials = requestCredentials,
            requestRedirect = requestRedirect,
            body = body,
            handler = { response ->
                if (response.status < 400) {
                    val json = response.json().await()
                    parse(json)
                } else {
                    throw BadStatusCodeException(response.status.toInt(), response.getProblemDescription())
                }
            }
        )

    suspend fun <T> requestAndHandleSuccess(
        url: String,
        method: String = "GET",
        headers: dynamic = json(),
        requestCredentials: RequestCredentials = RequestCredentials.SAME_ORIGIN,
        requestRedirect: RequestRedirect = RequestRedirect.FOLLOW,
        body: dynamic = null,
        handler: suspend (Response) -> T
    ): T =
        requestAndHandle(
            url = url,
            method = method,
            headers = headers,
            requestCredentials = requestCredentials,
            requestRedirect = requestRedirect,
            body = body,
            handler = { response ->
                if (response.status < 400) {
                    handler(response)
                } else {
                    throw BadStatusCodeException(response.status.toInt(), response.getProblemDescription())
                }
            }
        )

    suspend fun <T> requestAndHandle(
        url: String,
        method: String = "GET",
        headers: dynamic = json(),
        requestCredentials: RequestCredentials = RequestCredentials.SAME_ORIGIN,
        requestRedirect: RequestRedirect = RequestRedirect.FOLLOW,
        body: dynamic = null,
        handler: suspend (Response) -> T
    ): T {
        DevOptions.RandomHttpDelay.randomDelay()
        DevOptions.FailSpecificHttpRequests.failIfDesired(method, url)
        val chaosCausedFailure = GlobalOptions.chaosMode && Random.nextBoolean()
        val response = if (chaosCausedFailure) {
            console.log("CHAOS: ${d}method - ${d}url")
            throw ChaosModeException()
        } else {
            window.fetch(url, object : RequestInit {
                override var method: String? = method
                override var headers: dynamic = headers
                override var credentials: RequestCredentials? = requestCredentials
                override var redirect: RequestRedirect? = requestRedirect
                override var body: dynamic = body
            }).await()
        }
        return handler(response)
    }

    fun createUrlParameters(vararg values: Pair<String, Any?>): String =
        values.joinToString(separator = "&", prefix = "?") {
            val name = it.first
            val value = it.second?.toString()?.encodeUriComponent() ?: ""
            "${d}name=${d}value"
        }

    private fun String?.encodeUriComponent(): String = this?.let { encodeURIComponent(it) } ?: ""

    private suspend fun Response.getProblemDescription(): ProblemDescription? {
        return headers.get("Content-Type")
            ?.takeIf { it.contains("application/json") }
            ?.let {
                val json: dynamic = json().await()
                ProblemDescription(
                    json.title as String,
                    json.detail as String
                )
            }
    }

}

private external fun encodeURIComponent(str: String): String

class BadStatusCodeException(val statusCode: Int, val problemDescription: ProblemDescription?) : RuntimeException()
data class ProblemDescription(val title: String, val detail: String)
class ChaosModeException : RuntimeException()
