fun main() {
    chrome.webNavigation.onCompleted.addListener {
        console.log("Hello")
    }
}
