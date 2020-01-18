package dev.khalil.peekture.api

import dev.khalil.peekture.BuildConfig.PHOTOS_ENDPOINT
import dev.khalil.peekture.model.PhotosResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(PHOTOS_ENDPOINT)
    fun getPhotos(@Query("page") page: Int, @Query("client_id") apiKey: String): Single<List<PhotosResponse>>
}