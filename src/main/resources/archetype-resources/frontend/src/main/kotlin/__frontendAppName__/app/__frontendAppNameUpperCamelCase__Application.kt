package ${frontendAppName}.app

import com.linkedplanet.uikit.util.Async
import com.linkedplanet.uikit.wrapper.aui.auiSpinner
import ${frontendAppName}.page.${frontendAppName}MainPage
import ${frontendAppName}.reducer.${frontendAppNameUpperCamelCase}Handler
import kotlinx.coroutines.delay
import kotlinx.html.id
import model.Notification
import react.*
import react.dom.*
import react.redux.*
import reducer.NotificationHandler
import redux.*


external interface ${frontendAppNameUpperCamelCase}ApplicationStateProps : Props {
    var screen: ${frontendAppNameUpperCamelCase}Application.Screen
    var notifications: List<Notification>
}

external interface ${frontendAppNameUpperCamelCase}ApplicationDispatchProps : Props

external interface ${frontendAppNameUpperCamelCase}ApplicationProps : ${frontendAppNameUpperCamelCase}ApplicationStateProps, ${frontendAppNameUpperCamelCase}ApplicationDispatchProps

val ${frontendAppName}Application =
    rConnect<
        ${frontendAppNameUpperCamelCase}AppState,
        RAction,
        WrapperAction,
        Props,
        ${frontendAppNameUpperCamelCase}ApplicationStateProps,
        ${frontendAppNameUpperCamelCase}ApplicationDispatchProps,
        ${frontendAppNameUpperCamelCase}ApplicationProps>(
        { state, _ ->
            screen = state.screen
            notifications = state.notifications
        },
        { _, _ -> }
        )(${frontendAppNameUpperCamelCase}Application::class.react)

class ${frontendAppNameUpperCamelCase}Application(props: ${frontendAppNameUpperCamelCase}ApplicationStateProps) :
    RComponent<${frontendAppNameUpperCamelCase}ApplicationStateProps, State>(props) {

    override fun componentDidMount() {
        // load initial data and switch to main screen once loaded
        Async.complete(
            taskName = "load-data",
            taskFun = {
                // TODO usually perform a rest call to load initial app data
                delay(1000)
            },
            completeFun = { _ ->
                // TODO set the data as app state via the handler
                ${frontendAppNameUpperCamelCase}Handler.setScreen(Screen.Main)
            },
            // TODO might want to use a more descriptive error message
            catchFun = { error -> NotificationHandler.show(error, "Loading initial data") }
        )
    }

    override fun RBuilder.render() {
        renderMainComponent()
        renderNotifications()
    }

    private fun RBuilder.renderNotifications() {
        if (props.notifications.isNotEmpty()) {
            // TODO define how to render notifications
        }
    }

    private fun RBuilder.renderMainComponent() {
        val screen = props.screen
        div {
            attrs.key = "main-component"
            attrs.id = "main-component"
            when (screen) {
                is Screen.Loading ->
                    if (props.notifications.isEmpty()) {
                        div("util-align-loading-spinner") {
                            auiSpinner {
                                attrs["size"] = "medium"
                            }
                        }
                    }
                is Screen.Main ->
                    // TODO you can pass data into the page function using the screen object as DTO
                    ${frontendAppName}MainPage()
            }
        }
    }

    sealed class Screen {
        object Loading : Screen()
        object Main : Screen()
    }

    companion object {
        val appStore = createStore<${frontendAppNameUpperCamelCase}AppState, RAction, dynamic>(
            rootReducer(), ${frontendAppNameUpperCamelCase}AppState(), compose(
                rEnhancer(),
                js("if(window.__REDUX_DEVTOOLS_EXTENSION__ )window.__REDUX_DEVTOOLS_EXTENSION__ ();else(function(f){return f;});")
            )
        )
    }

}
