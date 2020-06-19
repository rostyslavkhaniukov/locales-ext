@file:JsQualifier("chrome.webNavigation")

package chrome.webNavigation

external val onCompleted: RuntimeInstalledEvent

external interface RuntimeInstalledEvent {
    fun addListener(listener: () -> Unit)
}
