package com.zenjob.android.browsr.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zenjob.android.browsr.utils.Constants.TAG

abstract class BrowsrViewModel : ViewModel() {
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun onError(message: String) {
        Log.d(TAG,"Error occurred with message: $message" )
        errorMessage.value = message
        isLoading.value = false
    }
}
