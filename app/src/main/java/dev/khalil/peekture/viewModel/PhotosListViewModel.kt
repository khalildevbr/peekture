package dev.khalil.peekture.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PhotosListViewModel : ViewModel() {

    private val photosLiveData = MutableLiveData<List<String>>()
    val photos: LiveData<List<String>> = photosLiveData

    init {
        insertPhotos()
    }

    private fun insertPhotos() {
        photosLiveData.value = getPhotos()
    }


    private fun getPhotos(): ArrayList<String> {
        return arrayListOf(
            "https://images.unsplash.com/photo-1562887009-92ca32b341c6?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjExMDY5OX0",
            "https://images.unsplash.com/photo-1579357926184-97099e1d7829?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjExMDY5OX0",
            "https://images.unsplash.com/photo-1579359023882-87ec25fb0875?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjExMDY5OX0",
            "https://images.unsplash.com/photo-1579359575322-9a1d5ca0234e?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjExMDY5OX0",
            "https://images.unsplash.com/photo-1579362094446-3db5b312f2ca?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjExMDY5OX0",
            "https://images.unsplash.com/photo-1579326284859-6e1a4b741425?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjExMDY5OX0"
        )
    }

}