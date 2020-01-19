package dev.khalil.peekture.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.khalil.peekture.model.PhotosResponse
import dev.khalil.peekture.model.PhotosUi
import dev.khalil.peekture.repository.PhotosRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PhotosListViewModel @Inject constructor(private val repository: PhotosRepository) :
    ViewModel() {

    private val photosLiveData = MutableLiveData<List<PhotosUi>>()
    val photos: LiveData<List<PhotosUi>> = photosLiveData

    private val loadingLiveData = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = loadingLiveData

    private val errorLiveData = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = errorLiveData

    private val compositeDisposable = CompositeDisposable()
    private var actualPage = 1

    init {
        getPhotos()
    }

    fun retry() {
        getPhotos()
    }

    private fun getPhotos() {
        compositeDisposable.add(repository.getPhotos(actualPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onSubscribe() }
            .doFinally { loadingLiveData.value = false }
            .subscribe({ responseList -> convertResponseToUiModel(responseList) },
                { errorLiveData.value = true })
        )
    }

    private fun onSubscribe() {
        loadingLiveData.value = true
        errorLiveData.value = false
    }

    private fun convertResponseToUiModel(responseList: List<PhotosResponse>) {
        photosLiveData.value = responseList.map { photoResponse ->
            PhotosUi(photoResponse.urls.small)
        }
    }
}