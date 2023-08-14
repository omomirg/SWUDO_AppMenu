package com.example.guruproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FirstFragmentViewModel : ViewModel() {
    // 버튼 눌린 개수를 저장하는 LiveData
    val waterButtonCount = MutableLiveData<Pair<Int, Int>>()
}
