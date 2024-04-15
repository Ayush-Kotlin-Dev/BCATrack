package ggv.ayush.bcatrack.data.repository

import kotlinx.coroutines.flow.Flow


interface DataStoreOperations {
    suspend fun saveOnBoardingState(completed : Boolean)
    fun getOnBoardingState() : Flow<Boolean>
}