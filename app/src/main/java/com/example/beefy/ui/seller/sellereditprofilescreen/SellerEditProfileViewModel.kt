package com.example.beefy.ui.seller.sellereditprofilescreen

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beefy.data.repository.SellerRepository
import com.example.beefy.data.repository.UserPrefRepository
import com.example.beefy.data.response.DetailPenjualResponse
import com.example.beefy.data.response.EditPPPenjualResponse
import com.example.beefy.data.response.EditPenjualResponse
import com.example.beefy.utils.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.http.Multipart

class SellerEditProfileViewModel(
    private val userPrefRepository: UserPrefRepository,
    private val sellerRepository: SellerRepository
) : ViewModel() {

    private var token: String = ""

    private var _userProfile = MutableLiveData<Resource<DetailPenjualResponse>>()
    val userProfile: LiveData<Resource<DetailPenjualResponse>> = _userProfile

    private var _sellerEditPPPenjual = MutableLiveData<Resource<EditPPPenjualResponse>>()
    val sellerEditPPPenjual: LiveData<Resource<EditPPPenjualResponse>> = _sellerEditPPPenjual

    private var _sellerEditPenjual = MutableLiveData<Resource<EditPenjualResponse>>()
    val sellerEditPenjual: LiveData<Resource<EditPenjualResponse>> = _sellerEditPenjual

//    private var _sellerEditPPPenjualStatus = MutableLiveData<Boolean>()
//    val sellerEditPPPenjualStatus : LiveData<Boolean> = _sellerEditPPPenjualStatus
//
//    private var _sellerEditPenjualStatus = MutableLiveData<Boolean>()
//    val sellerEditPenjualStatus : LiveData<Boolean> = _sellerEditPenjualStatus

    var sellerEditPPPenjualStatus: Boolean = false
    var sellerEditPenjualStatus: Boolean = false

    private var _confirmEditStatus = MutableLiveData<Boolean>()
    val confirmEditStatus: LiveData<Boolean> = _confirmEditStatus


    init {
        getDetailPenjual()
    }

    fun getDetailPenjual() {
        viewModelScope.launch {
            userPrefRepository.getIdType().collect {
                token = it
                if (!it.isNullOrBlank()) {
                    sellerRepository.getDetailPenjual(it.toInt()).collect {
                        _userProfile.postValue(it)
                    }
                }
            }
        }
    }

    fun editPPPenjual(image: MultipartBody.Part) {
        viewModelScope.launch {
            sellerRepository.editPPPenjual(token.toRequestBody("text/plain".toMediaType()), image)
                .collect {
                    _sellerEditPPPenjual.postValue(it)
                }

        }
    }

    fun editPenjual(
        alamatLengkap: String,
        jamBuka: String,
        jamTutup: String,
        metodePembayaran: String,
        rekening: String
    ) {
        viewModelScope.launch {
            sellerRepository.editPenjual(
                token,
                alamatLengkap,
                jamBuka,
                jamTutup,
                metodePembayaran,
                rekening
            ).collect {
                _sellerEditPenjual.postValue(it)
            }

        }
    }

    fun confirmEditProfile(
        image: MultipartBody.Part,
        alamatLengkap: String,
        jamBuka: String,
        jamTutup: String,
        metodePembayaran: String,
        rekening: String
    ) {
        editPPPenjual(image)
        editPenjual(alamatLengkap, jamBuka, jamTutup, metodePembayaran, rekening)

    }

}