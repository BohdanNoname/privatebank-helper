package com.nedashkivskyi.privatebankhelper.utils

sealed class Resource<T>(data: T?, message: String?) {
    data class Success<T>(val data: T?): Resource<T>(data = data, message = null)
    data class Error<T>(val message: String): Resource<T>(data = null, message = message)
}