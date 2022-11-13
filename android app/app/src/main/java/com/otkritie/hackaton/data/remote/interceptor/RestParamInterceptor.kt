package com.otkritie.hackaton.data.remote.interceptor

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.Response
import okio.Buffer
import okio.BufferedSink

class RestParamsInterceptor(
    private val json: Json
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (request.method != METHOD_POST_IDENTIFIER) {
            return chain.proceed(request)
        }
        val builder = request.newBuilder()
        val newRequest = request.body?.let {
            val requestBody = it.toRequestBody()
            builder.method(request.method, requestBody).build()
        } ?: builder.build()
        return chain.proceed(newRequest)
    }

    private fun RequestBody.toRequestBody(): RequestBody {
        val buffer = Buffer()
        writeTo(buffer)
        val content = buffer.readUtf8()
        buffer.run { clear(); close() }
        val jsonObject = runCatching { json.decodeFromString<JsonObject>(content) }.getOrNull()
        return jsonObject.toString().toRequestBodyWithoutCharsetInMediaType(JSON_MEDIA_TYPE.toMediaType())
    }

    private fun String.toRequestBodyWithoutCharsetInMediaType(mediaType: MediaType): RequestBody {
        val bytes = toByteArray(Charsets.UTF_8)
        return object : RequestBody() {
            override fun contentType() = mediaType
            override fun contentLength() = bytes.size.toLong()
            override fun writeTo(sink: BufferedSink) {
                sink.write(bytes, 0, bytes.size)
            }
        }
    }

    private companion object {
        const val JSON_MEDIA_TYPE = "application/json"
        const val METHOD_POST_IDENTIFIER = "POST"
    }
}
