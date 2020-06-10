package com.eraqi.siatask

import android.app.Application
import com.eraqi.siatask.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
* this class run when the application starts , it simply represents the application lifecycle, it's responsible for initilaiz
 * some objects or libraries that would be used in the application, here we initialized Koin (dependency injection library) for example.
 */
class App:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(module)
        }
    }
}