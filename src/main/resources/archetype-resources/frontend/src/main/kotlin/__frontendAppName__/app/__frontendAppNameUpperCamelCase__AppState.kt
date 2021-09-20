package ${frontendAppName}.app

import ${frontendAppName}.reducer.screen
import model.Notification
import reducer.notifications
import redux.*
import kotlin.reflect.KProperty1

data class ${frontendAppNameUpperCamelCase}AppState(
    val notifications: List<Notification> = emptyList(),
    val screen: ${frontendAppNameUpperCamelCase}Application.Screen = ${frontendAppNameUpperCamelCase}Application.Screen.Loading
)

fun appReducer() = combinePropertyReducers(
    mapOf(
        ${frontendAppNameUpperCamelCase}AppState::notifications to ::notifications,
        ${frontendAppNameUpperCamelCase}AppState::screen to ::screen
    )
)

fun rootReducer(): Reducer<${frontendAppNameUpperCamelCase}AppState, RAction> = { state, action ->
    if (action is Clear) {
        appReducer().invoke(${frontendAppNameUpperCamelCase}AppState(), action)
    } else {
        appReducer().invoke(state, action)
    }
}

private fun <S, A, R> combinePropertyReducers(reducers: Map<KProperty1<S, R>, Reducer<*, A>>): Reducer<S, A> {
    return combineReducers(reducers.mapKeys { it.key.name })
}

object Clear : RAction
