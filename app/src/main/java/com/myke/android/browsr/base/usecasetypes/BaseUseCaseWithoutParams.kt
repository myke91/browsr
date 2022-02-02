package com.myke.android.browsr.base.usecasetypes

interface BaseUseCaseWitOuthParams<R> {
    suspend fun run() : R
}