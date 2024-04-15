package ggv.ayush.bcatrack.domain.repository

import kotlinx.coroutines.flow.Flow


interface DataStoreOperations {
    suspend fun saveOnBoardingState(completed : Boolean)
    fun getOnBoardingState() : Flow<Boolean>
}