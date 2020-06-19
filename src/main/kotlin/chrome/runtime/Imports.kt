@file:JsQualifier("chrome.runtime")

package chrome.runtime

external val onInstalled: RuntimeInstalledEvent

external interface RuntimeInstalledEvent {
    fun addListener(listener: () -> Unit)
}