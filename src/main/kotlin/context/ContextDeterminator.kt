package context

import kotlin.browser.window

const val CONTEXT_PARAM = "context"

enum class Contexts(val value: String) {
    Content("Content"),
    Popup("Popup"),
}

class ContextDeterminator {
    fun getContext(): Contexts {
        val sPageURL = window.location.search.substring(1);
        val sURLVariables = sPageURL.split('&');
        for (element in sURLVariables) {
            val sParameterName = element.split('=');
            if (sParameterName[0] == CONTEXT_PARAM) {
                return Contexts.valueOf(sParameterName[1])
            }
        }

        return Contexts.Content;
    }
}
