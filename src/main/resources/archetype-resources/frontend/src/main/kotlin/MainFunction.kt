import org.w3c.dom.Element
import ${frontendAppName}.app.*
import react.dom.*
import react.redux.provider

@OptIn(ExperimentalJsExport::class)
@JsExport
fun start${frontendAppNameUpperCamelCase}(container: Element) {
    kotlinext.js.require("base.scss")
    kotlinext.js.require("util.scss")
    kotlinext.js.require("@fortawesome/fontawesome-free/js/all.js")

    kotlinext.js.require("app-${frontendAppNameKebabCase}.scss")

    render(container) {
        provider(${frontendAppNameUpperCamelCase}Application.appStore) {
            ${frontendAppName}Application {}
        }
    }
}


@OptIn(ExperimentalJsExport::class)
@JsExport
fun stop${frontendAppNameUpperCamelCase}(container: Element) {
    ${frontendAppNameUpperCamelCase}Application.appStore.dispatch(Clear)
    unmountComponentAtNode(container)
}
