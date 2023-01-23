package com.luisfagundes.framework.base

import com.luisfagundes.framework.network.DataState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber

abstract class SuspendUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    @Suppress("TooGenericExceptionCaught")
    suspend operator fun invoke(parameters: P): DataState<R> {
        return try {
            withContext(coroutineDispatcher) {
                execute(parameters).let {
                    DataState.Success(it)
                }
            }
        } catch (e: Exception) {
            Timber.d(e)
            DataState.Error(e)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): R
}