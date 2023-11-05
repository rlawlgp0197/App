package com.example.jhstudy

import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.BundleCompat

inline fun <reified T : Parcelable> Bundle?.safeParcelable(key: String): T? =
    this?.let { BundleCompat.getParcelable(this, key, T::class.java) }

inline fun <reified T : Parcelable> Bundle?.safeParcelableList(key: String): ArrayList<T>? =
    this?.let { BundleCompat.getParcelableArrayList(this, key, T::class.java) }