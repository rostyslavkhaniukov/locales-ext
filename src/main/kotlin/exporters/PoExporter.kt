package exporters

class PoExporter<K, V> : ExporterContract<K, V> {
    override fun export(map: HashMap<K, V>): String {
        var result = ""
        map.forEach { entry ->
            run {
                result += "msgctxt \"${entry.key}\"\n"
                result += "msgid \"${entry.value}\"\n"
                result += "msgstr \"${entry.value}\"\n\n"
            }
        }
        return result
    }
}
