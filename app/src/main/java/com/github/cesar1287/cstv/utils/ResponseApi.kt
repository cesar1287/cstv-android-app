package com.github.cesar1287.cstv.utils

sealed class ResponseApi {
    class Success(var data: Any?) : ResponseApi()
    class Error(val message: Int? = null) : ResponseApi()
}