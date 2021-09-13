package component.main

import app.AppState
import component.pages.mainPage.mainPage
import model.*
import reducers.*
import imports.atlaskit.flag.*
import imports.atlaskit.icon.*
import kotlinx.browser.*
import kotlinx.html.id
import react.*
import react.dom.div
import react.redux.rConnect
import redux.*

interface MainStateProps : RProps {
    var screen: MainComponent.Screen
    var notifications: List<Notification>
}

interface MainDispatchProps : RProps
interface MainProps : MainStateProps, MainDispatchProps

val mainComponent: RClass<MainProps> =
    rConnect<AppState, RAction, WrapperAction, RProps, MainStateProps, MainDispatchProps, MainProps>(
        { state, _ ->
            screen = state.screen
            notifications = state.notifications
        },
        { _, _ -> }
    )(MainComponent::class.js.unsafeCast<RClass<MainProps>>())

class MainComponent(props: MainProps) : RComponent<MainProps, RState>(props) {

    override fun RBuilder.render() {
        localStorage.setItem("theme", "theme-light")
        document.documentElement?.className = "theme-light"
        div {
            attrs.id = "content"
            props.screen.render(this)
        }
        renderNotifications()
    }

    private fun RBuilder.renderNotifications() =
        FlagGroup {
            attrs.onDismissed = { NotificationHandler.remove(it) }
            props.notifications.forEach { notification ->
                AutoDismissFlag {
                    when (notification.type) {
                        NotificationType.SUCCESS -> attrs.icon = CheckCircleIcon { attrs.primaryColor = "green" }
                        NotificationType.INFO -> attrs.icon = QuestionCircleIcon { attrs.primaryColor = "blue" }
                        NotificationType.WARNING -> attrs.icon = WarningIcon { attrs.primaryColor = "orange" }
                        NotificationType.ERROR -> attrs.icon = ErrorIcon { attrs.primaryColor = "red" }
                    }
                    attrs.title = notification.title
                    attrs.description = notification.description
                }
            }
        }

    enum class Screen {
        Loading {
            override fun render(rb: RBuilder) {
                rb.apply {
                    div {
                        +"Loading ..."
                    }
                }
            }
        },
        Main {
            override fun render(rb: RBuilder) {
                rb.apply {
                    mainPage {}
                }
            }
        };

        abstract fun render(rb: RBuilder)
    }
}
