package com.otkritie.hackaton.data.remote.datasource.delegate

import com.otkritie.hackaton.R
import com.otkritie.hackaton.core.ResourceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceDelegate @Inject constructor(
    val resourceManager: ResourceManager
) {

    suspend inline fun <T> sendRequest(
        crossinline request: suspend () -> Response<T>,
        onSuccess: (T?) -> Unit,
        onFailure: (String) -> Unit
    ) {
        try {
            val response = withContext(Dispatchers.IO) { request() }
            if (response.isSuccessful) {
                onSuccess(response.body())
            } else {
                onFailure(
                    response.message().ifEmpty { resourceManager.getString(R.string.network_error_empty_message) })
            }
        } catch (e: HttpException) {
            onFailure(resourceManager.getString(R.string.network_error_something_went_wrong))
        } catch (e: IOException) {
            onFailure(resourceManager.getString(R.string.network_error_bad_connection))
        }
    }
}
