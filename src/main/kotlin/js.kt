import exporters.JsonExporter
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import kotlin.browser.document

fun main() {

    val exporter = JsonExporter<String, String>()
    val map = hashMapOf(Pair("a", "b"), Pair("c", "d"))
    console.log(exporter.export(map))

    val rootEl = document.querySelector("body") as HTMLElement
    val modal = Modal()
    rootEl.append(modal.getModal())

    val buttonSave = modal.getButtonSave()

    rootEl.addEventListener("contextmenu",  {
        e ->
        run {
            val target = e.target as HTMLElement

            e.preventDefault()
            val constantName = getConstantName(target)
            if (isValueConstant(constantName)) {
                setConstantAttribute(target, constantName)

                modal.toggleModal()
                modal.setConstantValue(constantName)
            }
        }
    })

    buttonSave.addEventListener("click",
    {
        val input = modal.getInput()
        val value = input.value
        if (value !== "") {
            val constantName = modal.getConstantValue()
            setNewValue(constantName, value)

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

fun setNewValue (key: String, value: String) {
    console.log("${key}: $value")
}
