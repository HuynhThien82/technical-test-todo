package com.joblogic.joblogictodo.product.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joblogic.domain.common.model.ErrorType
import com.joblogic.domain.common.model.ResultWrapper
import com.joblogic.domain.product.usecase.GenerateSellListUseCase
import com.joblogic.domain.product.usecase.GetSellListUseCase
import com.joblogic.joblogictodo.R
import com.joblogic.joblogictodo.product.model.ProductModel
import com.joblogic.joblogictodo.product.model.mapper.toProductModelList
import io.reactivex.rxjava3.disposables.CompositeDisposable

class ToSellViewModel(
    private val getSellListUseCase: GetSellListUseCase,
    private val generateSellListUseCase: GenerateSellListUseCase
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val _sellList = MutableLiveData<MutableList<ProductModel>>()
    val sellList: LiveData<MutableList<ProductModel>>
        get() = _sellList

    // ViewModel should hold string id instead of string because locale can be changed
    private val _errorMessage = MutableLiveData<Int?>()
    val errorMessage: LiveData<Int?>
        get() = _errorMessage

    fun generateData() {
        compositeDisposable.add(generateSellListUseCase().subscribe{ result ->
            if (result is ResultWrapper.Error) {
                val stringId = when (result.error) {
                    ErrorType.Database -> R.string.database_error
                    else -> R.string.an_error_occur
                }
                _errorMessage.postValue(stringId)
            }
        })
    }

    fun getSellList() {
        _errorMessage.postValue(null)
        compositeDisposable.add(getSellListUseCase().subscribe { result ->
            when (result) {
                is ResultWrapper.Success -> {
                    _sellList.postValue(result.data.toProductModelList())
                }
                is ResultWrapper.Error -> {
                    Log.e(TAG, "getSellList: ${result.error}")
                    // TODO handle more error
                    val stringId = when(result.error) {
                        ErrorType.Network -> R.string.no_internet
                        else -> R.string.an_error_occur
                    }
                    _errorMessage.postValue(stringId)
                }
            }
        })

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    companion object {
        private val TAG = this::class.java.name
    }
}