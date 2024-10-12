package dev.hossain.timeline

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okio.buffer
import okio.source
import java.io.File

class Parser {
    fun parse(file: File) {
        println("Parsing...")

        if (file.exists()) {
            val source = file.source().buffer()
            val json = source.readUtf8()

            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val adapter = moshi.adapter(MyDataClass::class.java)
            val data = adapter.fromJson(json)

            println("Parsed data: $data")
        } else {
            println("File not found")
        }
    }
}

data class MyDataClass(
    val field1: String,
    val field2: Int
)