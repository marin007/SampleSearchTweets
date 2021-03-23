package com.example.demo_list_tweets

import android.app.Application
import com.example.demo_list_tweets.presentation.di.networkModule
import com.example.demo_list_tweets.presentation.di.repositoriesModule
import com.example.demo_list_tweets.presentation.di.useCasesModule
import com.example.demo_list_tweets.presentation.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initialiseDependencyInjection()
    }

    private fun initialiseDependencyInjection() {
        startKoin {
            androidContext(this@MyApplication)
            modules(
                repositoriesModule,
                viewModelsModule,
                networkModule,
                useCasesModule
            )
        }
    }
}
