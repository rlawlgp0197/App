package com.example.jhstudy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageSharedViewModel:ViewModel() {
    val sharedImageData = MutableLiveData<String>()
}