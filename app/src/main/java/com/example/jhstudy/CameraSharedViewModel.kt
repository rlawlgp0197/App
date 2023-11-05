package com.example.jhstudy

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CameraSharedViewModel:ViewModel() {
    val sharedCameraData = MutableLiveData<Uri>()
}