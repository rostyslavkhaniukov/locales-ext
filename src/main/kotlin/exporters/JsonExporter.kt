package exporters

class JsonExporter<K, V> : ExporterContract<K, V> {
    override fun export(map: HashMap<K, V>): String {
        val list = ArrayList<String>()
        map.forEach { entry -> list.add("\t\"${entry.key}\": \"${entry.value}\"") }
        return "{\n" + list.joinToString(",\n") + "\n}\n"
    }
}
