package kg.rickandmorty.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kg.rickandmorty.api.CharacterRepository
import kg.rickandmorty.api.ServiceAPI
import kg.rickandmorty.fragments.DetailsCharacter
import kg.rickandmorty.model.Character
import kg.rickandmorty.utils.Constant.BASE_URL
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)

@Module
object AppModule {

    @Provides
    fun providesBaseUrl() : String{
        return BASE_URL
    }

    @Provides
    fun providesGsonFactory() : Converter.Factory{
        return GsonConverterFactory.create()
    }

    @Provides
    fun providesRetrofit(baseURL: String, converterFactory: Converter.Factory) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun providesRetrofitService(retrofit: Retrofit) : ServiceAPI{
        return retrofit.create(ServiceAPI::class.java)
    }

    @Provides
    fun providesRepository(serviceAPI: ServiceAPI) : CharacterRepository {
        return CharacterRepository(serviceAPI)
    }

}