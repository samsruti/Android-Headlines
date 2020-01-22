package com.byjus.headlines.assignment.samsruti

import android.app.Application
import com.byjus.headlines.assignment.samsruti.di.AppComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class NewsAppApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        val appComponent = AppComponent()

        startKoin {
            printLogger() // Koin Logger
            androidContext(this@NewsAppApplication)

            modules(appComponent.getComponentModuleList())
        }

    }


}
