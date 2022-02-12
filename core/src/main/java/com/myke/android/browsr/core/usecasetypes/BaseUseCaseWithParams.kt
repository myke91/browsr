package com.myke.android.browsr.core.usecasetypes

interface BaseUseCaseWithParams<P, R> {
    suspend fun run(params : P) : R
}