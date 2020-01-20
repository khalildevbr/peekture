package dev.khalil.peekture

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.khalil.peekture.model.PhotosResponse
import dev.khalil.peekture.repository.PhotosRepository
import dev.khalil.peekture.viewModel.PhotosListViewModel
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PhotosListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockRepository: PhotosRepository

    @Mock
    private lateinit var mockResponse: List<PhotosResponse>

    private lateinit var viewModel: PhotosListViewModel

    private var actualPage = 1

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        MockitoAnnotations.initMocks(this)

        viewModel = PhotosListViewModel(mockRepository)
    }


    @Test
    fun `get Photos from Api`() {
        //given
        Mockito.`when`(mockRepository.getPhotos(actualPage)).thenReturn(Single.just(mockResponse))
        //when
        viewModel.getPhotos()
        //then
        assertEquals(mockResponse.size, viewModel.photos.value?.size)
    }

    @After
    fun finally() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }
}