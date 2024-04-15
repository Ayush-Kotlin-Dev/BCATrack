package ggv.ayush.bcatrack.DI

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ggv.ayush.bcatrack.data.repository.DataStoreOperationImpl
import ggv.ayush.bcatrack.data.repository.DataStoreOperations
import ggv.ayush.bcatrack.data.repository.Repository
import ggv.ayush.bcatrack.domain.use_cases.UseCases
import ggv.ayush.bcatrack.domain.use_cases.readonboarding.ReadOnBoardingUseCase
import ggv.ayush.bcatrack.domain.use_cases.save_onboarding.SaveOnBoardingCase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        context: Context
    ) : DataStoreOperations {
        return DataStoreOperationImpl(context = context )
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository) : UseCases {
        return UseCases(
            saveOnBoardingCase = SaveOnBoardingCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
        )
    }

        @Provides
        fun provideContext(@ApplicationContext app: Context): Context {
            return app
        }


}