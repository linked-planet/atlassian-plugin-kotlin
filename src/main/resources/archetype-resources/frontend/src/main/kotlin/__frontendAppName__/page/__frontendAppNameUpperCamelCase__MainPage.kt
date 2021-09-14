package ${frontendAppName}.page

import react.*
import react.dom.*


class ${frontendAppNameUpperCamelCase}MainPage :
    RComponent<${frontendAppNameUpperCamelCase}MainPage.Props, ${frontendAppNameUpperCamelCase}MainPage.State>() {

    override fun RBuilder.render() {
        // TODO the main part of your frontend goes here
        div {
            +"Visit us at "
            a("https://www.linked-planet.com", target = "_blank") {
                +"https://www.linked-planet.com"
            }
        }
    }

    interface Props : RProps {
    }

    interface State : RState {
    }

}

fun RBuilder.${frontendAppName}MainPage() =
    child(${frontendAppNameUpperCamelCase}MainPage::class) {
    }
