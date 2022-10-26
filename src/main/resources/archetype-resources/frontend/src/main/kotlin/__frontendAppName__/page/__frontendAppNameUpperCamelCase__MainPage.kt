package ${frontendAppName}.page

import react.*
import react.dom.*


class ${frontendAppNameUpperCamelCase}MainPage : RComponent<Props, State>() {

    override fun RBuilder.render() {
        // TODO the main part of your frontend goes here
        div {
            +"Visit us at "
            a("https://www.linked-planet.com", target = "_blank") {
                +"https://www.linked-planet.com"
            }
        }
    }

}

fun RBuilder.${frontendAppName}MainPage() =
    child(${frontendAppNameUpperCamelCase}MainPage::class) {
    }
