@file:JsQualifier("chrome.storage")

package chrome.storage

import kotlin.js.Promise

external val sync: StorageArea

external val local: StorageArea

external val managed: StorageArea

external interface StorageArea {
    fun get(keys: dynamic = definedExternally): Promise<dynamic>

    fun set(keys: dynamic, function: () -> Unit): Promise<Unit>

    fun remove(keys: dynamic): Promise<Unit>

    fun clear(): Promise<Unit>
}