package com.ss.tictacgame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
object GameData {
    private var _gameModel : MutableLiveData<Game> = MutableLiveData()
    var gameModel : LiveData<Game> = _gameModel
    var myID = ""


    fun saveGameModel(model : Game){
        _gameModel.postValue(model)

        if(model.gameId!="-1"){
            Firebase.firestore.collection("tictacGames")
                .document(model.gameId)
                .set(model)
        }

    }

    fun fetchGameModel(){

        gameModel.value?.apply {
            if(gameId!="-1"){
                Firebase.firestore.collection("tictacGames")
                    .document(gameId)
                    .addSnapshotListener { value, error ->
                        val model = value?.toObject(Game::class.java)
                        _gameModel.postValue(model)
                    }
            }
        }

    }
}