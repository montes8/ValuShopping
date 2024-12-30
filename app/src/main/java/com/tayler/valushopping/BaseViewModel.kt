package com.tayler.valushopping

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    val errorLiveData = MutableLiveData<Throwable>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun execute(func: suspend () -> Unit)=
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loadingLiveData.postValue(true)
                func()
                loadingLiveData.postValue(false)
            } catch (ex: Exception) {
                ex.printStackTrace()
                loadingLiveData.postValue(false)
                errorLiveData.postValue(ex)
            }
        }

    fun executeNotProgress(func: suspend () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                func()
            } catch (ex: Exception) {
                errorLiveData.postValue(ex)
            }
        }

    fun executeSuspendNotError(func: suspend () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                func()
            } catch (ex: Exception) {
               Log.d("tagError","$ex")
            }
        }

}