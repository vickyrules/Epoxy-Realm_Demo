package com.mine.realmsample

import android.app.Application
import androidx.activity.viewModels
import com.mine.realmsample.main.MainViewModel
import dagger.hilt.android.HiltAndroidApp
import io.realm.kotlin.Realm


@HiltAndroidApp
class SampleApplication:Application() {
    override fun onCreate() {
        super.onCreate()
    }

}