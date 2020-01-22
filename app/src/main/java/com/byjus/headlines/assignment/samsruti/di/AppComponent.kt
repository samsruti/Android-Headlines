package com.byjus.headlines.assignment.samsruti.di

import com.byjus.headlines.assignment.samsruti.BuildConfig
import com.byjus.headlines.assignment.samsruti.database.HeadlinesAppDataBase
import com.byjus.headlines.assignment.samsruti.network.NewsApiService
import com.byjus.headlines.assignment.samsruti.repository.HeadlineRepository
import com.byjus.headlines.assignment.samsruti.ui.headlines.HeadlinesViewModel
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


const val CONNECT_TIMEOUT = 10L
const val BASE_URL = "https://newsapi.org/"
const val API_KEY = BuildConfig.NEWS_API_KEY


class AppComponent {

    fun getComponentModuleList(): List<Module> {
        return listOf(networkModule, repositoryModule, roomModule, viewModelModule)
    }

    val networkModule = module {

        single {
            val authenticatorApiService = Interceptor { chain ->
                val interceptorURL = chain.request().url
                    .newBuilder()
                    .addQueryParameter("apiKey", API_KEY)
                    .build()

                val interceptorRequest = chain.request()
                    .newBuilder()
                    .url(interceptorURL)
                    .build()

                chain.proceed(interceptorRequest)
            }

            val newsApiClient = OkHttpClient().newBuilder()
                .addInterceptor(authenticatorApiService)
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(newsApiClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }



        single { GsonBuilder().create() }

        single {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
            OkHttpClient.Builder().apply {
                connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                retryOnConnectionFailure(true)
                addInterceptor(httpLoggingInterceptor)
            }.build()
        }

        factory { get<Retrofit>().create(NewsApiService::class.java) }
    }

    val repositoryModule = module {
        factory { HeadlineRepository(get(), get()) }
    }

    val roomModule = module {
        single { HeadlinesAppDataBase.getInstance(get()) }
        single { get<HeadlinesAppDataBase>().getDao() }
    }

    val viewModelModule = module {
        viewModel { HeadlinesViewModel(get()) }
    }
}

