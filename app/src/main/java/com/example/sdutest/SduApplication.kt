package com.example.sdutest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import javax.inject.Provider
import coil.ImageLoader
import coil.ImageLoaderFactory

@HiltAndroidApp
class SduApplication: Application(), ImageLoaderFactory {
    @Inject
    lateinit var imageLoader: Provider<ImageLoader>

    override fun onCreate() {
        super.onCreate()
        // Initialize Sync; the system responsible for keeping data in the app up to date.
//        Sync.initialize(context = this)
    }

    override fun newImageLoader(): ImageLoader = imageLoader.get()
}
