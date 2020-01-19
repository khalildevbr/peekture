package dev.khalil.peekture.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.android.AndroidInjection
import dev.khalil.peekture.R
import dev.khalil.peekture.databinding.ActivityPhotosBinding
import dev.khalil.peekture.extensions.gone
import dev.khalil.peekture.extensions.visible
import dev.khalil.peekture.model.PhotosUi
import dev.khalil.peekture.view.adapter.PhotosListAdapter
import dev.khalil.peekture.view.utils.EndlessRecyclerViewScrollListener
import dev.khalil.peekture.viewModel.PhotosListViewModel
import javax.inject.Inject

class PhotosListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: PhotosListViewModel

    private lateinit var binding: ActivityPhotosBinding
    private val adapter by lazy { PhotosListAdapter() }

    private var errorOnce = true

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_photos)

        initRecyclerView()
        initObservers()
        initListeners()
    }

    private fun initListeners() {
        binding.errorLayout.retryButton.setOnClickListener {
            viewModel.retry()
            errorOnce = false
        }
    }

    private fun initObservers() {
        viewModel.photos.observe(this, Observer { photos -> photosObserver(photos) })
        viewModel.loading.observe(this, Observer { isLoading -> loading(isLoading) })
        viewModel.error.observe(this, Observer { hasError -> showError(hasError) })
    }

    private fun initRecyclerView() {
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE

        binding.photosRecyclerView.addOnScrollListener(endlessScrollListener(layoutManager))

        binding.photosRecyclerView.layoutManager = layoutManager
        binding.photosRecyclerView.adapter = adapter
    }

    private fun showError(hasError: Boolean) {
        if (hasError) {
            binding.errorLayout.errorState.visible()
            binding.photosRecyclerView.gone()
        } else {
            binding.errorLayout.errorState.gone()
        }
    }

    private fun photosObserver(photos: List<PhotosUi>) {
        binding.photosRecyclerView.visible()
        adapter.addPhotos(photos)
        binding.errorLayout.errorState.gone()
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

    private fun endlessScrollListener(
        layoutManager: StaggeredGridLayoutManager): EndlessRecyclerViewScrollListener {
        return object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                viewModel.getMorePhotos()
            }
        }
    }
}
