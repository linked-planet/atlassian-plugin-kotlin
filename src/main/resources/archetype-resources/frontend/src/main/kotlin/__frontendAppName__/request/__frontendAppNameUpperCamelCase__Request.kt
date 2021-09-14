package ${frontendAppName}.request

import ${frontendAppName}.model.*
import util.RequestUtil.requestAndParseResult
import kotlin.js.json

object ${frontendAppNameUpperCamelCase}Request {

    // TODO suspend functions to make rest calls and parsing Json to model classes
    // TODO example for reference:
//    suspend fun getIssue(issueKey: String): Issue =
//        requestAndParseResult(
//            url = "$BASE_PATH/issue/$issueKey",
//            headers = json(
//                "Accept" to "application/json"
//            ),
//            parse = ::parseIssue
//        )

//    private fun parseIssue(json: dynamic): Issue {
//        val attachmentsJson = json.attachments as Array<dynamic>
//        return Issue(
//            key = json.key as String,
//            reporter = json.reporter as String,
//            assignee = json.assignee as String,
//            created = json.created as String,
//            attachments = attachmentsJson.map {
//                @Suppress("RemoveExplicitTypeArguments")
//                JSON.parse<Attachment>(JSON.stringify(it))
//            }
//        )
//    }

}

