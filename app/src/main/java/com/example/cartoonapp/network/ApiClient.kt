package com.example.cartoonapp.network

import com.example.cartoonapp.network.response.GetCharactersPageResponse
import com.example.cartoonapp.network.response.GetCharacterByIdResponse
import com.example.cartoonapp.network.response.GetEpisodeByIdResponse
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

    suspend fun getEpisodeById(episodeId: Int): SimpleResponse<GetEpisodeByIdResponse> {
        return safeApiCall { cartoonAppService.getEpisodeById(episodeId) }
    }

    suspend fun getEpisodeRange(episodeRange: String): SimpleResponse<List<GetEpisodeByIdResponse>> {
        return safeApiCall { cartoonAppService.getEpisodeRange(episodeRange) }
    }

    private inline fun<T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        }catch (e:Exception) {
            SimpleResponse.failure(e)
        }
    }

}