package com.puneetkaur.testapp.utils

sealed class Response<T>(val data:T? = null, val message: String?=null){

    class Loading<T>: Response<T>()

    class Success<T>(data: T): Response<T>(data = data)

    class Error<T>(errorsMessage: String) : Response<T>(message = errorsMessage)
}
