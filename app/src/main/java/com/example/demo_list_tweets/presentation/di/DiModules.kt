package com.example.demo_list_tweets.presentation.di

import android.content.Context
import com.example.demo_list_tweets.R
import com.example.demo_list_tweets.data.interceptors.AuthInterceptor
import com.example.demo_list_tweets.data.interceptors.NoConnectionInterceptor
import com.example.demo_list_tweets.data.remote.TweetsRemoteApi
import com.example.demo_list_tweets.data.repository.TweetsRemoteApiImpl
import com.example.demo_list_tweets.domain.repository.TweetRepository
import com.example.demo_list_tweets.domain.usecases.GetTweetByKeywordUseCase
import com.example.demo_list_tweets.presentation.searchtweets.SearchTweetsViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val viewModelsModule: Module = module {
    viewModel {
        SearchTweetsViewModel(get())
    }
}

val repositoriesModule: Module = module {
    single { createGlobalCommonRepository(get()) }
}

fun createGlobalCommonRepository(remoteApi: TweetsRemoteApi): TweetRepository {
    return TweetsRemoteApiImpl(
        remoteApi
    )
}

val useCasesModule: Module = module {

    factory {
        GetTweetByKeywordUseCase(
            tweetRepository = get()
        )
    }
}

val networkModule = module {

    fun createAPIURl(context: Context): String {
        return context.resources.getString(R.string.api_url)
    }

    single {
        MoshiConverterFactory.create(
            Moshi.Builder().add(
                KotlinJsonAdapterFactory()
            ).build()
        ) as Converter.Factory
    }
    single {
        OkHttpClient.Builder().apply {
            connectTimeout(60L, TimeUnit.SECONDS)
            readTimeout(60L, TimeUnit.SECONDS)
            addInterceptor(
                NoConnectionInterceptor(
                    androidContext()
                )
            )
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            addInterceptor(AuthInterceptor(androidContext()))

        }.build()
    }
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        Retrofit.Builder()
            .baseUrl(createAPIURl(androidContext()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(get())
            .addConverterFactory(get())
            .build()
    }
    single { get<Retrofit>().create(TweetsRemoteApi::class.java) }


}

