package br.com.whosplayer.app.whosplayer.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.whosplayer.databinding.ActivityWhosPlayerBinding

class WhosPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWhosPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        binding = ActivityWhosPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
