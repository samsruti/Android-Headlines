package com.byjus.headlines.assignment.samsruti.di


import com.byjus.headlines.assignment.samsruti.database.HeadlinesAppDao
import com.byjus.headlines.assignment.samsruti.network.NewsApiService
import com.byjus.headlines.assignment.samsruti.repository.HeadlineRepository
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MockedAppComponentTest{
    fun getRetrofitComponent(mockApi: String) = module {

        single { GsonBuilder().create() }

        single {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
            OkHttpClient.Builder().apply {
                retryOnConnectionFailure(true)
                addInterceptor(httpLoggingInterceptor)
            }.build()
        }

        single {
            Retrofit.Builder()
                .baseUrl(mockApi)
                .addConverterFactory(GsonConverterFactory.create(get()))
                .client(get())
                .build()
        }

        factory { get<Retrofit>().create<NewsApiService>() }
    }

    fun getRepositoryComponent(dao: HeadlinesAppDao) = module {
        factory { HeadlineRepository(get(), dao) }
    }
}

