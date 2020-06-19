import org.w3c.dom.get
import kotlin.browser.window
import kotlin.browser.document;

fun main() {
    console.log("Hello")
    val bla = document.getElementById("UserRolesModal")
    console.log(bla)
    console.log(window.get("allConstants"))

    val contexts = arrayOf("selection")
}
