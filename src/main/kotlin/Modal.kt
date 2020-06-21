import dispatcher.EventDispatcher
import dispatcher.Events
import org.w3c.dom.Element
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLInputElement
import kotlin.browser.document

class Modal(eventDispatcher: EventDispatcher) {
    private val modal = createElement("div", "modal-wrapper")
    private val constantSpan = createElement("span", "constant-value")
    private val input = createElement("input", "modal-input") as HTMLInputElement
    private val buttonSave = createButton("button", "button-save", "Save")

    private fun createElement (tagName: String, className: String): Element {
        val el = document.createElement(tagName)
        el.classList.add(className)

        return el;
    }

    private fun createElement (tagName: String, className: String, text: String): Element {
        val el = createElement(tagName, className)
        el.textContent = text

        return el;
    }

    private fun createButton (tagName: String, className: String, text: String): Element {
        val el = createElement(tagName, className, text) as HTMLButtonElement
        el.type = "button"

        return el;
    }

    private fun buildModal () {
        val modalInner = createElement("div", "modal-inner")
        val modalTitle = createElement("p", "modal-title", "Change value for ")
        modalTitle.append(constantSpan)

        val innerContent = createElement("div", "modal-body")
        innerContent.append(input)
        innerContent.append(buttonSave)

        modalInner.append(modalTitle)
        modalInner.append(innerContent)
        modal.append(modalInner)
    }

    fun getModal() = modal
    fun getConstantValue() = constantSpan.innerHTML
    fun getButtonSave() = buttonSave
    fun getInput() = input

    private fun setConstantValue(value: String) {
        constantSpan.textContent = value
    }

    private fun clearInput () {
        input.value = ""
    }

    private fun toggleModal () {
        modal.classList.toggle("modal-wrapper--showed")
    }

    init {
        eventDispatcher.subscribe(Events.Invoked) { payload -> run {
            toggleModal()
            setConstantValue(payload as String)
        } }
        eventDispatcher.subscribe(Events.Finished) {
            run {
            toggleModal()
            clearInput()
        } }

        buildModal()
    }
}
