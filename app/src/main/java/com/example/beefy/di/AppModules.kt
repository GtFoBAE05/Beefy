package com.example.beefy.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.beefy.data.repository.AuthRepository
import com.example.beefy.data.repository.BuyerRepository
import com.example.beefy.data.repository.SellerRepository
import com.example.beefy.data.repository.UserPrefRepository
import com.example.beefy.data.source.local.UserPreference
import com.example.beefy.data.source.remote.ApiConfig
import com.example.beefy.ui.auth.loginscreen.LoginScreenViewModel
import com.example.beefy.ui.auth.registerbuyerscreen.RegisterBuyerViewModel
import com.example.beefy.ui.auth.registersellerscreen.RegisterSellerViewModel
import com.example.beefy.ui.auth.splashscreen.SplashScreenViewModel
import com.example.beefy.ui.buyer.BuyerChatsListScreen.BuyerChatsListViewModel
import com.example.beefy.ui.buyer.buyercheckoutscreen.BuyerCheckoutViewModel
import com.example.beefy.ui.buyer.buyerhomescreen.BuyerHomeScreenViewModel
import com.example.beefy.ui.buyer.buyerorderdetailscreen.BuyerOrderDetailViewModel
import com.example.beefy.ui.buyer.buyerorderstatusscreen.buyerorderstatuscomplete.BuyerOrderStatusCompleteViewModel
import com.example.beefy.ui.buyer.buyerorderstatusscreen.buyerorderstatusonprocess.BuyerOrderStatusOnProcessViewModel
import com.example.beefy.ui.buyer.buyerproductdetailscreen.BuyerProductDetailViewModel
import com.example.beefy.ui.buyer.buyerprofilescreen.BuyerProfileViewModel
import com.example.beefy.ui.buyer.buyerscanhistoryscreen.BuyerScanHistoryViewModel
import com.example.beefy.ui.buyer.buyerscanscreen.BuyerScanViewModel
import com.example.beefy.ui.buyer.buyersearchscreen.BuyerSearchViewModel
import com.example.beefy.ui.buyer.buyersearchscreen.buyersearchproductresult.BuyerSearchProductResultViewModel
import com.example.beefy.ui.buyer.buyersearchscreen.buyersearchstoreresult.BuyerSearchStoreResultViewModel
import com.example.beefy.ui.buyer.buyerstoredetailscreen.BuyerStoreDetailViewModel
import com.example.beefy.ui.buyer.buyeruploadpaymentproofscreen.BuyerUploadPaymentProofViewModel
import com.example.beefy.ui.seller.selleradditemscreen.SellerAddItemViewModel
import com.example.beefy.ui.seller.sellerchatslistscreen.SellerChatsListViewModel
import com.example.beefy.ui.seller.sellerdetailitemscreen.SellerDetailItemViewModel
import com.example.beefy.ui.seller.sellerdetailtransactionscreen.sellerdetailcompletetransactionscreen.SellerDetailCompleteTransactionViewModel
import com.example.beefy.ui.seller.sellerdetailtransactionscreen.sellerdetailprocesstransactionscreen.SellerDetailProcessTransactionViewModel
import com.example.beefy.ui.seller.sellerdetailtransactionscreen.sellerdetailwaitingtransactionscreen.SellerDetailWaitingTransactionViewModel
import com.example.beefy.ui.seller.selleredititemscreen.SellerEditItemViewModel
import com.example.beefy.ui.seller.sellerhomescreen.SellerHomeViewModel
import com.example.beefy.ui.seller.sellerprofilescreen.SellerProfileViewModel
import com.example.beefy.ui.seller.sellertransactionscreen.sellercompletetransactionscreen.SellerCompleteTransactionViewModel
import com.example.beefy.ui.seller.sellertransactionscreen.sellerprocesstransactionscreen.SellerProcessTransactionViewModel
import com.example.beefy.ui.seller.sellertransactionscreen.sellerwaitingtransactionscreen.SellerWaitingTransactionViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


private val Context.dataStore by preferencesDataStore(name = "user_preferences")


val apiModule = module {
    single { ApiConfig(androidContext().dataStore).getApiService() }
}

val userPreferenceModule = module {
    single { UserPreference(androidContext().dataStore) }
}

val repositoryModule = module {
    single { AuthRepository(get()) }
    single { BuyerRepository(get()) }
    single { SellerRepository(get()) }
    single { UserPrefRepository(get()) }

}

val viewModelModule = module {
    //auth
    viewModel { SplashScreenViewModel(get()) }
    viewModel { LoginScreenViewModel(get(), get()) }
    viewModel { RegisterBuyerViewModel(get(),get()) }
    viewModel { RegisterSellerViewModel(get(), get()) }

    //buyer
    viewModel { BuyerHomeScreenViewModel(get()) }
    viewModel { BuyerProfileViewModel(get()) }
    viewModel { BuyerChatsListViewModel(get())}
    viewModel { BuyerCheckoutViewModel(get())}
    viewModel { BuyerOrderDetailViewModel(get())}
    viewModel { BuyerOrderStatusOnProcessViewModel(get())}
    viewModel { BuyerOrderStatusCompleteViewModel(get())}
    viewModel { BuyerProductDetailViewModel(get())}
    viewModel { BuyerScanHistoryViewModel(get())}
    viewModel { BuyerScanViewModel(get())}
    viewModel { BuyerSearchStoreResultViewModel(get())}
    viewModel { BuyerSearchProductResultViewModel(get()) }
    viewModel { BuyerStoreDetailViewModel(get()) }
    viewModel { BuyerUploadPaymentProofViewModel(get()) }
    viewModel { BuyerSearchViewModel(get()) }


    //seller
    viewModel { SellerChatsListViewModel(get()) }
    viewModel { SellerAddItemViewModel(get(),get()) }
    viewModel { SellerDetailItemViewModel(get()) }
    viewModel { SellerDetailCompleteTransactionViewModel(get()) }
    viewModel { SellerDetailProcessTransactionViewModel(get()) }
    viewModel { SellerDetailWaitingTransactionViewModel(get()) }
    viewModel { SellerEditItemViewModel(get()) }
    viewModel { SellerHomeViewModel(get(),get()) }
    viewModel { SellerProfileViewModel(get()) }
    viewModel { SellerCompleteTransactionViewModel(get()) }
    viewModel { SellerProcessTransactionViewModel(get()) }
    viewModel { SellerWaitingTransactionViewModel(get()) }

}