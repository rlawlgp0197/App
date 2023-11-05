package com.example.jhstudy.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ListLiveData<T> : MutableLiveData<MutableList<T>>() {
    private val temp = mutableListOf<T>()

    init {
        value = temp
    }

    fun add(item: T) {
        temp.add(item)
        postValue(temp)
    }

    fun addSet(item: T) {
        temp.add(item)
        value = temp
    }

    fun addAll(items: List<T>) {
        temp.addAll(items)
        postValue(temp)
    }

    fun addAllSet(items: List<T>) {
        temp.addAll(items)
        value = temp
    }


    fun remove(item: T) {
        temp.remove(item)
        postValue(temp)
    }

    fun removeSet(item: T) {
        temp.remove(item)
        value = temp
    }

    fun clear() {
        temp.clear()
        postValue(temp)
    }

    fun clearSet() {
        temp.clear()
        value = temp
    }

}

fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> = this