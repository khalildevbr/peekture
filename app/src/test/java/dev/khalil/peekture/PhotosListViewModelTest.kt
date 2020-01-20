package dev.khalil.peekture

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
import java.lang.reflect.Type


@RunWith(MockitoJUnitRunner::class)
class PhotosListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockRepository: PhotosRepository

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
        val response: List<PhotosResponse> = getMockResponse()
        Mockito.`when`(mockRepository.getPhotos(actualPage)).thenReturn(Single.just(response))
        //when
        viewModel.getPhotos()
        //then
        assertEquals(response.size, viewModel.photos.value?.size)
    }

    private fun getMockResponse(): List<PhotosResponse> {
        val jsonObject = readFromFile("/response.json")
        val listType: Type = object : TypeToken<ArrayList<PhotosResponse>>() {}.getType()
        val list: List<PhotosResponse> = Gson().fromJson(jsonObject, listType)

        return list
    }


    private fun readFromFile(filename: String): String {
        val inputStream = javaClass.getResourceAsStream(filename)
        val stringBuilder = StringBuilder()
        var i: Int?
        val b = ByteArray(4096)
        while (inputStream?.read(b).also { i = it } != -1) {
            stringBuilder.append(String(b, 0, i!!))
        }
        return stringBuilder.toString()
    }

    @After
    fun finally() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }
}