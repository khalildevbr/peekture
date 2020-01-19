package dev.khalil.peekture.repository

import dev.khalil.peekture.BuildConfig.API_KEY
import dev.khalil.peekture.api.ApiService
import dev.khalil.peekture.model.PhotosResponse
import io.reactivex.Single
import javax.inject.Inject

class PhotosRepository @Inject constructor(private val api: ApiService) {

    fun getPhotos(page: Int): Single<List<PhotosResponse>> {
        return api.getPhotos(page, API_KEY)
    }
}