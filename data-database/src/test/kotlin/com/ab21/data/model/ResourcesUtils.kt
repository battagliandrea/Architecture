package com.ab21.data.model

import com.ab21.data.database.model.CacheItem
import java.nio.charset.StandardCharsets
import java.util.*

object ResourcesUtils {

    fun getCacheItemFromResources(filename: String) =
        CacheItem(key = "test", data = getJson(filename), ttl = Date().time)

    private fun getJson(fileName: String) =
        javaClass.classLoader
            ?.getResource("$fileName")
            ?.readText(StandardCharsets.UTF_8)
            .orEmpty()
}
