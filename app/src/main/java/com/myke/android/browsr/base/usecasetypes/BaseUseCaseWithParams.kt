package com.myke.android.browsr.base.usecasetypes

interface BaseUseCaseWithParams<P, R> {
    suspend fun run(params : P) : R
}