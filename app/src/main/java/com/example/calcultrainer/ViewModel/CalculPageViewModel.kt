package com.example.calcultrainer.ViewModel

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import com.example.calcultrainer.Model.msgHistorique
import com.example.calcultrainer.R
import kotlin.random.Random

class CalculPageViewModel() : ViewModel() {

    private var _score = 0
    private var _calcul = "+"
    private var _realResult = 9
    private var _resultSize = 1
    private var _correction: List<msgHistorique> = emptyList()
    private var _level : Int = 1


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

    val level: Int
        get() = _level

    private lateinit var mpSuccess:MediaPlayer;
    private lateinit var mpFail:MediaPlayer;


    fun initMp(context: Context){
        mpSuccess = MediaPlayer.create(context, R.raw.success1)
        mpFail = MediaPlayer.create(context, R.raw.wrong1)
    }

    fun checkResult(result: Int): Boolean {

        val checkValue = (result == realResult)
        println(checkValue)
        val newMsg: msgHistorique =
            msgHistorique(value = "$calcul = $result ", isTrue = checkValue, isCorrection = false)
        _correction += newMsg
        if (!checkValue) {
            val newMsg1: msgHistorique = msgHistorique(
                value = "$calcul = $realResult ",
                isTrue = false,
                isCorrection = true
            )
            _correction += newMsg1
            if (_level > 1)
            {
                mpFail.start()
                _level--
            }

        }
        else{
            if (_level < 100000)
            {
                mpSuccess.start()
                _level++

            }
        }

        generateCalcul()

        return checkValue
    }


    fun launchParty() {
        generateCalcul()

    }

    fun generateCalcul() {
        val int1 = Random.nextInt(0, 2*_level)
        val int2 = Random.nextInt(0, 2*_level)
        _calcul = "$int1 + $int2"
        _realResult = int1 + int2
        _resultSize = realResult.toString().length
    }

}