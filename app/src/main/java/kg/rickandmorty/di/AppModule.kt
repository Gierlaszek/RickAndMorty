package kg.rickandmorty.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kg.rickandmorty.utils.Constant.BASE_URL

@InstallIn(SingletonComponent::class)

@Module
object AppModule {

    @Provides
    fun providesBaseUrl() : String{
        return BASE_URL
    }

}