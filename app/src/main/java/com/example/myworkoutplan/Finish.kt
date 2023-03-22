package com.example.myworkoutplan

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toolbar

class Finish : AppCompatActivity() {
    var dialogObj :AlertDialog.Builder? = null
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        val a :androidx.appcompat.widget.Toolbar = findViewById(R.id.Actionbar2)
//       setSupportActionBar(a)
          setSupportActionBar(findViewById(R.id.Actionbar2))
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
      a.setNavigationOnClickListener{
            onBackPressed()
        }


//
//        binding?.textviewActionbar?.setNavigationOnClickListener {
//            onBackPressed()
//        }
        val btnObj :Button = findViewById(R.id.button)
        btnObj.setOnClickListener {
//            val intent = Intent(this@Finish,MainActivity::class.java)
//            startActivity(intent)
          // finish()
            openCustomDialog()
        }


    }
   fun  openCustomDialog(){
        dialogObj = AlertDialog.Builder(this@Finish)
       dialogObj?.setMessage("if you want to do more exercise then click on 'yes' or if you want to exit then clickOn 'exit'")
       dialogObj?.setPositiveButton("yes") { _, _ ->
           val intent = Intent(this@Finish, exercise_::class.java)
           startActivity(intent)
       }
       dialogObj?.setNegativeButton("exit") { _, _ ->
           finish()
       }
       dialogObj?.setCancelable(false)
       dialogObj?.create()
       dialogObj?.show()

    }


}