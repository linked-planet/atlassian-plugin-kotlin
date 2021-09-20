package ${frontendAppName}.reducer

import ${frontendAppName}.app.${frontendAppNameUpperCamelCase}Application
import ${frontendAppName}.app.${frontendAppNameUpperCamelCase}Application.Companion.appStore
import redux.RAction

// ------------------------------------------------------------------------------------------
// ACTIONS
// ------------------------------------------------------------------------------------------
class SetScreenAction(val screen: ${frontendAppNameUpperCamelCase}Application.Screen) : RAction

// ------------------------------------------------------------------------------------------
// REDUCER
// ------------------------------------------------------------------------------------------
fun screen(
    state: ${frontendAppNameUpperCamelCase}Application.Screen = ${frontendAppNameUpperCamelCase}Application.Screen.Loading,
    action: RAction
): ${frontendAppNameUpperCamelCase}Application.Screen =
    when (action) {
        is SetScreenAction -> action.screen
        else -> state
    }

// ------------------------------------------------------------------------------------------
// HANDLER
// ------------------------------------------------------------------------------------------
object ${frontendAppNameUpperCamelCase}Handler {
    fun setScreen(screen: ${frontendAppNameUpperCamelCase}Application.Screen) {
        appStore.dispatch(SetScreenAction(screen))
    }
}


