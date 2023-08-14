package com.example.guruproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SecondFragmentViewModel : ViewModel() {
    private val _waterButtonCount = MutableLiveData<Pair<Int, Int>>()
    val waterButtonCount: LiveData<Pair<Int, Int>>
        get() = _waterButtonCount

    init {
        _waterButtonCount.value = Pair(0, 3) // 초기값 설정
    }

    fun updateWaterButtonCount(checkedCount: Int) {
        val totalButtonCount = 9
        _waterButtonCount.value = Pair(checkedCount, totalButtonCount)
    }
}
