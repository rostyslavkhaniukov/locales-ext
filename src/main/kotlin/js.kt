import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import kotlin.browser.document

fun main() {
    val rootEl = document.querySelector("body") as HTMLElement
    val modal = Modal()
    val state = State()
    rootEl.append(modal.getModal())

    val buttonSave = modal.getButtonSave()

    rootEl.addEventListener("contextmenu", {
        e ->
        run {

            e.preventDefault()
            val target = e.target as HTMLElement
            val constantName = getConstantName(target)
            if (isValueConstant(constantName)) {
                state.setTargetElem(target)
                setConstantAttribute(target, constantName)

                modal.toggleModal()
                modal.setConstantValue(constantName)
            }
        }
    })

    modal.getModal().addEventListener("click", {
        e ->
        run {
            val target = e.target as HTMLElement
            if (!modal.isModalInner(target) && modal.isOpen()) {
                modal.toggleModal()
            }
        }
    })

    buttonSave.addEventListener("click", {
        val input = modal.getInput()
        val value = input.value
        if (value !== "") {
            val constantName = modal.getConstantValue()
            val targetEl = state.getTargetElem()
            replaceLocaleInTarget(targetEl, value)
            state.addNewConstant(constantName, value)
            state.setCurrentLocaleValue(constantName, value)

            modal.clearInput()
            modal.toggleModal()
        }
    })
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
