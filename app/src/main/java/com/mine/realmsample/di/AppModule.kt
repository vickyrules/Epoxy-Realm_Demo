package com.mine.realmsample.di

import android.app.Person
import android.os.Build
import androidx.annotation.RequiresApi
import com.mine.realmsample.data.model.ItemNew
import com.mine.realmsample.data.repository.MainRepository
import com.mine.realmsample.data.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    @RequiresApi(Build.VERSION_CODES.P)
    @Provides
    @Singleton
    fun provideRealm(): Realm {
        val realmConfig = RealmConfiguration.Builder(
            schema = setOf(ItemNew::class)
        ).name("demo.db")
            .compactOnLaunch()
            .build()
        return Realm.open(realmConfig)

    }


}