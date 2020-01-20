package dev.khalil.peekture

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.khalil.peekture.model.PhotosResponse
import java.lang.reflect.Type


class TestUtils {
    fun getMockResponse(): List<PhotosResponse> {
        val jsonObject = readFromFile("/response.json")
        val listType: Type = object : TypeToken<ArrayList<PhotosResponse>>() {}.getType()
        val list: List<PhotosResponse> = Gson().fromJson(jsonObject, listType)

        return list
    }

    fun readFromFile(filename: String): String {
        val inputStream = javaClass.getResourceAsStream(filename)!!
        val stringBuilder = StringBuilder()
        var i: Int
        val b = ByteArray(4096)
        while (inputStream.read(b).also { i = it } != -1) {
            stringBuilder.append(String(b, 0, i))
        }
        return stringBuilder.toString()
    }
}