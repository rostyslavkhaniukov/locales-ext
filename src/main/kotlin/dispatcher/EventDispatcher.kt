package dispatcher

class EventDispatcher {
    private val listeners: HashMap<Events, ArrayList<(dynamic) -> Unit>> = HashMap(10, 0.75F)

    fun subscribe(event: Events, listener: (dynamic) -> Unit): Function<Unit> {
        if (listeners[event] == null) {
            listeners[event] = ArrayList()
        }

        listeners[event]?.add(listener)

        return fun() {
            listeners[event]?.remove(listener)
        }
    }

    fun dispatch(event: Events, payload: dynamic) {
        listeners[event]?.forEach { fn -> fn(payload as Any) }
    }
}
