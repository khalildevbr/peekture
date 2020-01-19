package dev.khalil.peekture.view.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.android.AndroidInjection
import dev.khalil.peekture.R
import dev.khalil.peekture.databinding.ActivityPhotosBinding
import dev.khalil.peekture.extensions.gone
import dev.khalil.peekture.extensions.visible
import dev.khalil.peekture.model.PhotosUi
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
        viewModel.loading.observe(this, Observer { isLoading -> loading(isLoading) })
        viewModel.error.observe(this, Observer { error -> showError(error) })
    }

    private fun loading(isLoading: Boolean) {

        if (isLoading) {
            binding.loadingAnimation.visible()
            binding.loadingAnimation.playAnimation()
        } else {
            binding.loadingAnimation.gone()
            binding.loadingAnimation.pauseAnimation()

        }
    }

    private fun initRecyclerView() {
        binding.photosRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.photosRecyclerView.adapter = adapter
    }

    private fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun photosObserver(photos: List<PhotosUi>) {
        adapter.addPhotos(photos)
    }
}
