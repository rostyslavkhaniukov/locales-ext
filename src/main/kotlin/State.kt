import org.w3c.dom.HTMLElement

data class State (
    var targetElem: HTMLElement? = null,
    var constantsMap: HashMap<String, String> = HashMap(10, 0.75F),
    var currentLocaleValue: Pair<String, String>? = null
) {
    fun addNewConstant (key: String, value: String) {
        constantsMap[key] = value
    }

    fun setTargetElem (element: HTMLElement) {
        targetElem = element
    }
    fun setCurrentLocaleValue (key: String, value: String) {
        currentLocaleValue = Pair(key, value)
    }

    fun getTargetElem () = targetElem
    fun getCurrentLocaleValue () = currentLocaleValue
}
