package imports.aui

import kotlinx.html.HTMLTag
import react.*
import react.dom.*

inline fun RBuilder.auiSpinner(block: RDOMBuilder<HTMLTag>.() -> Unit): ReactElement = tag(block) {
    HTMLTag("aui-spinner", it, mapOf(), null, inlineTag = true, false)
}
