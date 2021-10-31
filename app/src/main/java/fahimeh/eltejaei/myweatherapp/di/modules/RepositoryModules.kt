package fahimeh.eltejaei.myweatherapp.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fahimeh.eltejaei.myweatherapp.network.ApiRepository
import fahimeh.eltejaei.myweatherapp.network.ApiRepositoryImp

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModules {

    @Binds
    abstract fun provideApiRepository(apiRepositoryImp: ApiRepositoryImp): ApiRepository

}