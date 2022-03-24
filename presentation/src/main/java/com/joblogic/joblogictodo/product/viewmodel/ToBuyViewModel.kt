package com.joblogic.joblogictodo.product.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joblogic.domain.common.model.ErrorType
import com.joblogic.domain.common.model.ResultWrapper
import com.joblogic.domain.product.usecase.GetBuyListUseCase
import com.joblogic.joblogictodo.R
import com.joblogic.joblogictodo.product.model.ProductModel
import com.joblogic.joblogictodo.product.model.mapper.toProductModelList
import io.reactivex.rxjava3.disposables.CompositeDisposable

class ToBuyViewModel(
    private val getBuyListUseCase: GetBuyListUseCase
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val _buyList = MutableLiveData<MutableList<ProductModel>>()
    val buyList: LiveData<MutableList<ProductModel>>
        get() = _buyList

    // ViewModel should hold string id instead of string because locale can be changed
    private val _errorMessage = MutableLiveData<Int?>()
    val errorMessage: LiveData<Int?>
        get() = _errorMessage

    fun getBuyList(){
        _errorMessage.postValue(null)
        getBuyListUseCase().subscribe { result ->
            when(result) {
                is ResultWrapper.Success -> {
                    _buyList.postValue(result.data.toProductModelList())
                }
                is ResultWrapper.Error -> {
                    Log.e(TAG, "getBuyList: ${result.error}")
                    // TODO handle more error
                    val stringId = when(result.error) {
                        ErrorType.Network -> R.string.no_internet
                        else -> R.string.an_error_occur
                    }
                    _errorMessage.postValue(stringId)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    companion object {
        private val TAG = this::class.java.name
    }
}