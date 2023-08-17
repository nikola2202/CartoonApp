package com.example.cartoonapp.network

import com.example.cartoonapp.GetCharactersPageResponse
import com.example.cartoonapp.network.data.GetCharacterByIdResponse
import retrofit2.Response

class ApiClient(
    private val cartoonAppService: Service
) {

    suspend fun getCharacterById(characterId:Int): SimpleResponse<GetCharacterByIdResponse> {
        return safeApiCall { cartoonAppService.getCharacterById(characterId) }
    }

    suspend fun getCharactersPage(pageIndex: Int): SimpleResponse<GetCharactersPageResponse> {
        return safeApiCall { cartoonAppService.getCharactersPage(pageIndex) }
    }

    private inline fun<T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        }catch (e:Exception) {
            SimpleResponse.failure(e)
        }
    }

}