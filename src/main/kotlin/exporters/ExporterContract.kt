package exporters

interface ExporterContract<K, V> {
    fun export(map: HashMap<K, V>): String
}
