package com.example.ht6_2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlin.random.Random

class FrViewModel: ViewModel() {
    val numbers = MutableLiveData<Int>()
    val secondsPassed = MutableLiveData<Int>()
    var i = 0
    var job: Job? = null
    var isRunning: Boolean = true
    fun start(){
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO){
            while(isActive)
                if(isRunning) {
                    numbers.postValue(Random.nextInt(1, 9))
                    delay(50)
                }
        }

        job = viewModelScope.launch(Dispatchers.IO){
            while (isActive)
                if (isRunning){
                    delay(10)
                    i += 1
                    secondsPassed.postValue(i)
                }
        }
    }
    fun reset(){
        numbers.postValue(-1)
        secondsPassed.postValue(0)
        i = 0
    }
    fun pause(){
        isRunning = false
    }
    fun run(){
        isRunning = true
    }
}