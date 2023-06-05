package com.example.beefy.ui.buyer.buyerorderstatusscreen.buyerorderstatusonprocess

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beefy.data.repository.BuyerRepository
import com.example.beefy.data.repository.UserPrefRepository
import com.example.beefy.data.response.BuyerOrderProductResponse
import com.example.beefy.utils.Resource
import kotlinx.coroutines.launch

class BuyerOrderStatusOnProcessViewModel(private val userPrefRepository: UserPrefRepository, private val buyerRepository: BuyerRepository):ViewModel() {

    private var _orderList = MutableLiveData<Resource<List<BuyerOrderProductResponse>>>()
    val orderList : LiveData<Resource<List<BuyerOrderProductResponse>>> = _orderList

    init {
        getOrderList()
    }

    fun getOrderList(){
        viewModelScope.launch {
            userPrefRepository.getIdType().collect{
                if(!it.isNullOrBlank()){
                    buyerRepository.buyerGetOrderInProcess(it.toInt()).collect{
                        _orderList.postValue(it)
                    }
                }
            }
        }
    }

}