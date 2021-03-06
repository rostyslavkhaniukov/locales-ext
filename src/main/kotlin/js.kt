import dispatcher.EventDispatcher
import dispatcher.Events
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import kotlin.browser.document

fun main() {
    val rootEl = document.querySelector("body") as HTMLElement
    val eventBus = EventDispatcher()
    val modal = Modal(eventBus)
    val state = State()
    rootEl.append(modal.getModal())

    rootEl.addEventListener("contextmenu", {
        e -> run {
            e.preventDefault()
            val target = e.target as HTMLElement
            val constantName = getConstantName(target)
            if (isValueConstant(constantName)) {
                state.setTargetElem(target)
                setConstantAttribute(target, constantName)

                eventBus.dispatch(Events.Invoked, constantName)
            }
        }
    })

    eventBus.subscribe(Events.Saved) {
        payload -> run {
            val (constantName, value) = payload as Pair<String, String>
            val targetEl = state.getTargetElem()

            replaceLocaleInTarget(targetEl, value)
            state.addNewConstant(constantName, value)
            state.setCurrentLocaleValue(constantName, value)
        }
    }
}

fun replaceLocaleInTarget(node: HTMLElement?, value: String) {
    if (node == null) return
    if (node.tagName == "INPUT" || node.tagName == "TEXTAREA") {
        (node as HTMLInputElement).placeholder = value
        return
    }

    node.firstChild?.textContent = value
}

fun getConstantName(node: HTMLElement): String {
    val constantAttribute = getConstantAttribute(node)
    if (constantAttribute != null) {
        return constantAttribute
    }

    if (node.tagName == "INPUT") return (node as HTMLInputElement).placeholder
    return node.firstChild?.textContent.toString()
}

fun getConstantAttribute (node: HTMLElement): String?  {
    return node.getAttribute("locale-const")
}

fun setConstantAttribute (node: HTMLElement, value: String)  {
    node.setAttribute("locale-const", value)
}

fun isValueConstant (constantName: String): Boolean {
    return (constantName.toUpperCase() == constantName)
}
