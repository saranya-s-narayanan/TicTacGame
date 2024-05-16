package com.ss.tictacgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.ss.tictacgame.databinding.ActivityMainBinding
import com.ss.tictacgame.databinding.ActivityOfflineGameBinding

class OfflineGameActivity : AppCompatActivity(),OnClickListener {

    private lateinit var binding: ActivityOfflineGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOfflineGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        


    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}