package com.example.calcultrainer.ViewModel

import androidx.lifecycle.ViewModel
import com.example.calcultrainer.Model.msgHistorique
import kotlin.random.Random

class CalculPageViewModel() : ViewModel() {

    private var _score = 0
    private var _calcul = "+"
    private var _realResult = 9
    private var _resultSize = 1
    private var _correction: List<msgHistorique> = emptyList()

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

    val correction: List<msgHistorique>
        get() = _correction

    fun checkResult(result: Int): Boolean {

        val checkValue = (result == realResult)
        println(checkValue)
        var newMsg: msgHistorique =
            msgHistorique(value = "$calcul = $result ", isTrue = checkValue, isCorrection = false)
        _correction += newMsg
        if (!checkValue) {
            var newMsg1: msgHistorique = msgHistorique(
                value = "$calcul = $realResult ",
                isTrue = checkValue,
                isCorrection = true
            )
            _correction += newMsg1
        }

        generateCalcul()

        return checkValue
    }


    fun launchParty() {
        generateCalcul()

    }

    fun generateCalcul() {
        val int1 = Random.nextInt(0, 100)
        val int2 = Random.nextInt(0, 100)
        _calcul = "$int1 + $int2"
        _realResult = int1 + int2
        _resultSize = realResult.toString().length
    }

}