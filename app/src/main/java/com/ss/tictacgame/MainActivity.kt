package com.ss.tictacgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ss.tictacgame.databinding.ActivityMainBinding
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)


        binding.playOffline.setOnClickListener {
            GameData.saveGameModel(
                Game(
                    gameStatus = GameStatus.JOINED
                )
            )
            val intent = Intent(this, OfflineGameActivity::class.java)
            startActivity(intent)
        }

        binding.playOnline.setOnClickListener {
            GameData.myID = "X"
            GameData.saveGameModel(
                Game(
                    gameStatus = GameStatus.CREATED,
                    gameId = Random.nextInt(1000..9999).toString()
                )
            )
            val intent = Intent(this, OfflineGameActivity::class.java)
            startActivity(intent)
        }


        binding.joinOnline.setOnClickListener {
            var gameId = binding.gameIdInput.text.toString()
            if(gameId.isEmpty()){
                binding.gameIdInput.setError("Please enter game ID")
                //return
            }else {
                GameData.myID = "O"
                Firebase.firestore.collection("tictacGames")
                    .document(gameId)
                    .get()
                    .addOnSuccessListener {
                        val model = it?.toObject(Game::class.java)
                        if (model == null) {
                            binding.gameIdInput.setError("Please enter valid game ID")
                        } else {
                            model.gameStatus = GameStatus.JOINED
                            GameData.saveGameModel(model)
                            val intent = Intent(this, OfflineGameActivity::class.java)
                            startActivity(intent)
                        }
                    }
            }
        }

    }
}