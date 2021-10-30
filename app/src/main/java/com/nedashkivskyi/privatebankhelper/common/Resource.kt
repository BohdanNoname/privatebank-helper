package com.nedashkivskyi.privatebankhelper.common

sealed class Resource<T>(data: T?, message: String?, isLoading: Boolean) {
    data class Success<T>(val data: T?):
        Resource<T>(data = data, message = null, isLoading = false)

    data class Error<T>(val message: String):
        Resource<T>(data = null, message = message, isLoading = false)

    data class Loading<T>(val isLoading: Boolean):
        Resource<T>(data = null, message = null, isLoading = isLoading)
}