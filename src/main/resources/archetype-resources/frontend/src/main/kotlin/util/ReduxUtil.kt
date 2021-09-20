package util

import react.*
import react.redux.rConnect
import redux.*
import kotlin.reflect.KClass

object ReduxUtil {

    fun <S, P : RProps> connect(appClass: KClass<*>, stateToProps: P.(S, RProps) -> Unit): RClass<RProps> =
        rConnect<S, RAction, WrapperAction, RProps, P, RProps, P>(
            stateToProps,
            { _, _ -> }
        )(appClass.js.unsafeCast<RClass<P>>())

}

