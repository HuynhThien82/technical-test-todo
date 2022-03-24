package com.joblogic.joblogictodo.user.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joblogic.domain.common.model.ErrorType
import com.joblogic.domain.common.model.ResultWrapper
import com.joblogic.domain.user.usecase.GetCallListUseCase
import com.joblogic.joblogictodo.R
import com.joblogic.joblogictodo.user.model.ContactModel
import com.joblogic.joblogictodo.user.model.mapper.toContactModelList
import io.reactivex.rxjava3.disposables.CompositeDisposable

class ToCallViewModel(
    private val getCallListUseCase: GetCallListUseCase
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val _callList = MutableLiveData<MutableList<ContactModel>>()
    val callList: LiveData<MutableList<ContactModel>>
        get() = _callList

    // ViewModel should hold string id instead of string because locale can be changed
    private val _errorMessage = MutableLiveData<Int?>()
    val errorMessage: LiveData<Int?>
        get() = _errorMessage

    fun getCallList() {
        _errorMessage.postValue(null)
        compositeDisposable.add(getCallListUseCase().subscribe { result ->
            when(result) {
                is ResultWrapper.Success -> {
                    _callList.postValue(result.data.toContactModelList())
                }
                is ResultWrapper.Error -> {
                    Log.e(TAG, "getCallList: ${result.error}" )
                    val stringId = when (result.error) {
                        ErrorType.Database -> R.string.database_error
                        else -> R.string.an_error_occur
                    }
                    _errorMessage.postValue(stringId)
                }
            }
        })
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    companion object {
        private val TAG = this::class.java.name
    }
}