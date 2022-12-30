package com.dd.mdassignmentapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    val myLiveModel = MutableLiveData<MyModel>()

    init {
        myLiveModel.value = MyModel(0)
    }
}