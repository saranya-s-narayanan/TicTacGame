package com.ss.tictacgame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object GameData {
    private var _gameModel : MutableLiveData<Game> = MutableLiveData()
    var gameModel : LiveData<Game> = _gameModel


    fun saveGameModel(model : Game){
        _gameModel.postValue(model)
    }

}