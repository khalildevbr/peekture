package dev.khalil.peekture.di.module

import dagger.Module
import dagger.Provides
import dev.khalil.peekture.viewModel.PhotosListViewModel

@Module
class PhotosListActivityModule {

    @Provides
    fun providesPhotosListViewModel() = PhotosListViewModel()
}