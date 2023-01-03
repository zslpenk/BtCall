package com.autolink.btcall.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PhoneNumberHistoryViewModel : ViewModel() {
    private val number: LiveData<Int> = MutableLiveData()
}