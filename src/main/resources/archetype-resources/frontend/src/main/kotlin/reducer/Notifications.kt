package reducer

import ${frontendAppName}.app.${frontendAppNameUpperCamelCase}Application.Companion.appStore
import model.*
import redux.RAction
import util.BadStatusCodeException

// ACTIONS
class AddNotification(val notification: Notification) : RAction
class RemoveNotification(val string: String) : RAction
object ClearAllNotifications : RAction

// reducer
fun notifications(state: List<Notification> = emptyList(), action: RAction): List<Notification> =
    when (action) {
        is AddNotification -> state + action.notification
        is RemoveNotification -> state.drop(1)
        else -> state
    }

// HANDLER
object NotificationHandler {

    fun show(throwable: Throwable, title: String) {
        appStore.dispatch(ClearAllNotifications)
        val problemDescription = throwable.let { (it as? BadStatusCodeException)?.problemDescription }
        val problemTitle = problemDescription?.title ?: "Unknown Error"
        show(
            NotificationType.ERROR,
            title,
            problemTitle + " - " + (problemDescription?.detail ?: "Please contact an Administrator.")
        )
    }

    fun show(type: NotificationType, title: String, description: String) {
        appStore.dispatch(AddNotification(Notification(type, title, description)))
    }

    fun remove(string: String) {
        appStore.dispatch(RemoveNotification(string))
    }
}


