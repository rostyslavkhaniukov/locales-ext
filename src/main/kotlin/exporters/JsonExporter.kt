package exporters

class JsonExporter<K, V> : ExporterContract<K, V> {
    override fun export(map: HashMap<K, V>): String {
        var result = "{\n"
        map.forEach { entry ->
            run {
                result += "\t\"${entry.key}\": \"${entry.value}\"\n"
            }
        }
        result += "}\n"

        return result
    }
}
