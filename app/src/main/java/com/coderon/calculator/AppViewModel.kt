package com.coderon.calculator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {

    private val _firstNumber: MutableStateFlow<Double?> = MutableStateFlow(null)
    val firstNumber = _firstNumber.asStateFlow()

    private val _secondNumber: MutableStateFlow<Double?> = MutableStateFlow(null)
    val secondNumber = _secondNumber.asStateFlow()


    private val _action: MutableStateFlow<String> = MutableStateFlow("")
    val action = _action.asStateFlow()

    fun setFirstNumber(input: Double) {
        _firstNumber.update { input }
    }

    fun setSecondNumber(input: Double) {
        _secondNumber.update { input }
    }

    fun setAction(input: String) {
        _action.update { input }
    }

    fun resetAll(){
        _action.update { "" }
        _firstNumber.update { null }
        _secondNumber.update { null }
    }

    fun getResult(): Double {
        return when (_action.value) {
            "−" -> {
                _firstNumber.value!! - _secondNumber.value!!
            }

            "+" -> {
                _firstNumber.value!! + _secondNumber.value!!
            }

            "÷" -> {
                _firstNumber.value!! / _secondNumber.value!!
            }

            "×" -> {
                _firstNumber.value!! * _secondNumber.value!!
            }

            else -> {
                0.0
            }
        }
    }


}