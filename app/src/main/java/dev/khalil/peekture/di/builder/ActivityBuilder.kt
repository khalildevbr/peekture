package dev.khalil.peekture.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.khalil.peekture.di.module.PhotosListActivityModule
import dev.khalil.peekture.view.ui.PhotosListActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [PhotosListActivityModule::class])
    abstract fun bindPhotosListActivity(): PhotosListActivity

}