package com.example.myworkoutplan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myworkoutplan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding :ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.start?.setOnClickListener {
            val  intent = Intent(this,Exercise::class.java)
            startActivity(intent)
        }
        binding?.bmi?.setOnClickListener {
            val intent = Intent(this@MainActivity,Body_Mass_Index::class.java)
            startActivity(intent)
        }

        binding?.history?.setOnClickListener{
            val intent = Intent(this,exerciseHistory::class.java)

            startActivity(intent)
        }

    }
}


