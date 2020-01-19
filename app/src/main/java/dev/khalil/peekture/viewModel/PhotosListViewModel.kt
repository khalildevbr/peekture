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

    private val errorLiveData = MutableLiveData<String>()
    val error: LiveData<String> = errorLiveData

    private val compositeDisposable = CompositeDisposable()
    private var actualPage = 1

    init {
        getPhotos()
    }

    private fun getPhotos() {
        compositeDisposable.add(repository.getPhotos(actualPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loadingLiveData.value = true }
            .doFinally { loadingLiveData.value = false }
            .subscribe({ responseList -> convertResponseToUiModel(responseList) },
                { exception -> errorLiveData.value = exception.message })
        )
    }

    private fun convertResponseToUiModel(responseList: List<PhotosResponse>) {
        photosLiveData.value = responseList.map { photoResponse ->
            PhotosUi(photoResponse.urls.small)
        }
    }
}