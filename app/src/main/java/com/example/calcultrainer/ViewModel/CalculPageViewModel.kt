package com.example.calcultrainer.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.calcultrainer.Model.Party
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class CalculPageViewModel() : ViewModel() {

    private val _partyState = MutableStateFlow(Party())
    val partyState: StateFlow<Party> = _partyState


    private var _score = 0
    private var _calcul = "+"
    private var _realResult = 9
    private var _resultSize = 1

    init {
        launchParty()
    }



    val score: Int
        get() = _score

    val calcul: String
        get() = _calcul

    val realResult: Int
        get() = _realResult
    val resultSize: Int
        get() = _resultSize

    fun checkResult(result: Int) : Boolean {

        val checkValue = (result == realResult)
        println(checkValue)
        generateCalcul()

        return  checkValue
    }


    fun launchParty() {
        generateCalcul()

    }

    fun generateCalcul() {
        val int1 = Random.nextInt(0,100)
        val int2 = Random.nextInt(0, 100)
        _calcul = "$int1 + $int2"
        _realResult = int1 + int2
        _resultSize = realResult.toString().length
    }

}