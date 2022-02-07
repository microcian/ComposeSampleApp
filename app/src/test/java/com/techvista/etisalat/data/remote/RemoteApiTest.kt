package com.techvista.etisalat.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.techvista.etisalat.TestMockResponseFileReader
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteApiTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var service: RemoteApi
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RemoteApi::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun searchSuccess() {
        enqueueResponse(
            "success_json.json"
        )
        runBlocking {
            val response = service.getPhotos()
            assertThat(response.isNotEmpty()).isTrue()
        }
    }

    @Test
    fun emptyCase() {
        enqueueResponse(
            "failure_json.json"
        )
        runBlocking {
            val response = service.getPhotos()
            assertThat(response.isEmpty()).isTrue()
        }
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val source= TestMockResponseFileReader(fileName)
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse
                .setBody(source.content )
        )
    }
}