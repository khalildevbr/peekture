package dev.khalil.peekture.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.android.AndroidInjection
import dev.khalil.peekture.R
import dev.khalil.peekture.databinding.ActivityPhotosBinding
import dev.khalil.peekture.view.adapter.PhotosListAdapter
import dev.khalil.peekture.viewModel.PhotosListViewModel
import javax.inject.Inject

class PhotosListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: PhotosListViewModel

    private lateinit var binding: ActivityPhotosBinding
    private val adapter by lazy { PhotosListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_photos)

        initRecyclerView()
        initObservers()
    }

    private fun initObservers() {
        viewModel.photos.observe(this, Observer { photos -> photosObserver(photos) })
    }

    private fun photosObserver(photos: List<String>) {
        adapter.addPhotos(photos)
    }

    private fun initRecyclerView() {
        binding.photosRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.photosRecyclerView.adapter = adapter
    }
}
