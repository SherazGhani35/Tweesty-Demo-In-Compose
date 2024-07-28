package com.sheraz.tweesty.di

import com.sheraz.tweesty.api.TweetApi
import com.sheraz.tweesty.repository.TweetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class RepositoryModule {

    @Provides
    fun provideRepository(api: TweetApi): TweetRepository {
        return TweetRepository(api)
    }


}