import org.w3c.dom.HTMLElement
import kotlin.browser.document

fun main() {
    val rootEl = document.querySelector("body") as HTMLElement
    val modal = Modal()
    rootEl.append(modal.getModal())

    val buttonSave = modal.getButtonSave()

    rootEl.addEventListener("contextmenu", {
        e ->
        run {
            val target = e.target as HTMLElement

            e.preventDefault()
            val constantName = getConstantName(target)
            if (isValueConstant(constantName)) {
                setConstantAttribute(target, constantName)

                modal.toggleModal()
                modal.setTriggerElement(target)
                modal.setConstantValue(constantName)
            }
        }
    })

    rootEl.addEventListener("click", {
        e ->
        run {
            val target = e.target as HTMLElement

            if (
                    modal.isOpen()
                    && target !== modal.getModalInner()
                    && !modal.getModalInner().contains(target)
            ) {
                modal.toggleModal()
            }
        }
    })

    buttonSave.addEventListener("click", {
        val input = modal.getInput()
        val value = input.value
        if (value !== "") {
            val constantName = modal.getConstantValue()
            setNewValue(constantName, value)

            modal.getTriggerElement().textContent = value
            modal.clearInput()
            modal.toggleModal()
        }
    })
}

fun getConstantName(node: HTMLElement): String {
    val constantAttribute = getConstantAttribute(node)
    if (constantAttribute != null) {
        return constantAttribute
    }

    if (node.tagName === "INPUT" || node.tagName === "TEXTAREA") {
        return node.getAttribute("placeholder").toString()
    }
    return node.firstChild?.textContent.toString()
}

fun getConstantAttribute(node: HTMLElement): String? {
    return node.getAttribute("locale-const")
}

fun setConstantAttribute(node: HTMLElement, value: String) {
    node.setAttribute("locale-const", value)
}

fun isValueConstant(constantName: String?): Boolean {
    return constantName?.toUpperCase() == constantName
}

fun setNewValue(key: String, value: String) {
    console.log("${key}: $value")
}
