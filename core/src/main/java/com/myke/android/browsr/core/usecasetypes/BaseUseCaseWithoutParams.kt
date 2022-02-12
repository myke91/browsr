package com.myke.android.browsr.core.usecasetypes

interface BaseUseCaseWitOuthParams<R> {
    suspend fun run() : R
}