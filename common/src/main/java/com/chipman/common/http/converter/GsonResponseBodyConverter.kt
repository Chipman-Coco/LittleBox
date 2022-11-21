package com.chipman.common.http.converter

import com.chipman.common.result.ApiResponse
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.ResponseBody
import retrofit2.Converter

class GsonResponseBodyConverter<T : Any>(
    private val gson: Gson,
    private val adapter: TypeAdapter<T>
) : Converter<ResponseBody, ApiResponse<T>> {

    override fun convert(value: ResponseBody): ApiResponse<T> {
        val jsonReader = gson.newJsonReader(value.charStream())
        value.use {
            jsonReader.beginObject()
            var errorCode = 0
            var errorMsg = ""
            var data: T? = null
            while (jsonReader.hasNext()) {
                when (jsonReader.nextName()) {
                    "errorCode" -> errorCode = jsonReader.nextInt()
                    "errorMsg" -> errorMsg = jsonReader.nextString()
                    "data" -> data = adapter.read(jsonReader)
                    else -> jsonReader.skipValue()
                }
            }
            jsonReader.endObject()
            return if (errorCode != 0) {
                ApiResponse.BizError(errorCode, errorMsg)
            } else if (data == null) {
                /**
                 * 由于接口会有"data":null的情况，这里兜底替换为Any()，保证Success里data的非空性
                 */
                @Suppress("UNCHECKED_CAST")
                ApiResponse.Success(Any()) as ApiResponse<T>
            } else {
                ApiResponse.Success(data)
            }
        }
    }
}