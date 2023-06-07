package com.litobumba.appjwtauth.auth

sealed class AuthResult<T>(val data: T? = null){
    class Authorized<T>(data: T? = null): AuthResult<T>(data)
    class Unauthorized<T>(data: T? = null): AuthResult<T>(data)
    class UnknownError<T>: AuthResult<T>()
}
