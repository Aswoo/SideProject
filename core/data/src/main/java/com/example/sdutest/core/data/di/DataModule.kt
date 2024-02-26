package com.example.sdutest.core.data.di

import JvmUnitTestFakeAssetManager
import android.content.Context
import com.droidknights.app2023.core.datastore.datasource.DefaultSessionPreferencesDataSource
import com.example.sdutest.core.common.network.Dispatcher
import com.example.sdutest.core.common.network.NiaDispatchers
import com.example.sdutest.core.data.api.GithubRawApi
import com.example.sdutest.core.data.api.fake.AssetsGithubRawApi
import com.example.sdutest.core.data.api.fake.FakeAssetManager
import com.example.sdutest.core.data.repository.DefaultPokeRepository
import com.example.sdutest.core.data.repository.DefaultSessionRepository
import com.example.sdutest.core.data.repository.DefaultSettingsRepository
import com.example.sdutest.core.data.repository.PokemonRepository
import com.example.sdutest.core.data.repository.SessionRepository
import com.example.sdutest.core.data.repository.SettingsRepository
import com.example.sdutest.core.datastore.datasource.SessionPreferencesDataSource
//import com.droidknights.app2023.core.data.api.GithubRawApi
//import com.example.sdutest.core.data.api.fake.AssetsGithubRawApi
//import com.droidknights.app2023.core.data.repository.ContributorRepository
//import com.droidknights.app2023.core.data.repository.DefaultContributorRepository
//import com.droidknights.app2023.core.data.repository.DefaultSessionRepository
//import com.droidknights.app2023.core.data.repository.DefaultSettingsRepository
//import com.droidknights.app2023.core.data.repository.DefaultSponsorRepository
//import com.droidknights.app2023.core.data.repository.SessionRepository
//import com.droidknights.app2023.core.data.repository.SettingsRepository
//import com.droidknights.app2023.core.data.repository.SponsorRepository
//import com.droidknights.app2023.core.datastore.datasource.DefaultSessionPreferencesDataSource
//import com.droidknights.app2023.core.datastore.datasource.SessionPreferencesDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataModule {

//    @Binds
//    abstract fun bindsContributorRepository(
//        repository: DefaultContributorRepository,
//    ): ContributorRepository
//
//    @Binds
//    abstract fun bindsSettingsRepository(
//        repository: DefaultSettingsRepository,
//    ): SettingsRepository

    @Binds
    abstract fun bindSessionLocalDataSource(
        dataSource: DefaultSessionPreferencesDataSource,
    ): SessionPreferencesDataSource

    @Binds
    abstract fun bindSessionRepository(impl: DefaultSessionRepository): SessionRepository

    @Binds
    abstract fun bindSettingRepository(impl: DefaultSettingsRepository): SettingsRepository

    @Binds
    abstract fun bindPokemonRepository(impl: DefaultPokeRepository): PokemonRepository

    @InstallIn(SingletonComponent::class)
    @Module
    internal object FakeModule {

//        @Provides
//        @Singleton
//        fun provideSponsorRepository(
//            githubRawApi: GithubRawApi,
//        ): SponsorRepository = DefaultSponsorRepository(githubRawApi)


        @Provides
        @Singleton
        fun providesFakeAssetManager(
            @ApplicationContext context: Context,
        ): FakeAssetManager = FakeAssetManager(context.assets::open)

//        @Provides
//        @Singleton
//        fun provideSessionRepository(
//            @Dispatcher(NiaDispatchers.IO) ioDispatcher: CoroutineDispatcher,
//            networkJson: Json,
//            sessionDataSource: SessionPreferencesDataSource,
//            fakeAssetManager: FakeAssetManager,
//        ): SessionRepository = DefaultSessionRepository(
//            ioDispatcher = ioDispatcher,
//            networkJson = networkJson,
//            sessionDataSource = sessionDataSource,
//            assets = JvmUnitTestFakeAssetManager,
//        )

        @Provides
        @Singleton
        fun provideGithubRawApi(
            @ApplicationContext context: Context,
        ): AssetsGithubRawApi = AssetsGithubRawApi(context)

    }
}
