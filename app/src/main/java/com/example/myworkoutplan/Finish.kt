package com.example.myworkoutplan

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

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
            openCustomDialog()
           //finish()
        }

        val dbdao =(application as ExerciseApplication).database.eDao()
        dateadddataToDatabase(dbdao)
    }

   @SuppressLint("SuspiciousIndentation")
   private fun  openCustomDialog(){

       dialogObj = AlertDialog.Builder(this@Finish)
           dialogObj?.setMessage("if you want to do more exercise then click on 'yes' or if you want to exit then clickOn 'exit'")
           dialogObj?.setPositiveButton("yes") { _, _ ->
               val intent = Intent(this@Finish, Exercise::class.java)
               startActivity(intent)
               finish()
           }
           dialogObj?.setNegativeButton("exit") { _, _ ->
               finish()
           }
           dialogObj?.setCancelable(false)
           dialogObj?.create()
           dialogObj?.show()


   }
    private fun dateadddataToDatabase( dao:ExerciseDao){
        // send through dao as entity obj.
        lifecycleScope.launch {
              val a = returnDate()
          dao.insertExercise(ExerciseLIst(date = a.toString()))
            Toast.makeText(this@Finish,"data ${" $a "}added in dataBase",Toast.LENGTH_SHORT).show()

//          dao.insertExercise(ExerciseLIst(date.toString()))
//
//            dao.insertExercise(ExerciseLIst(date = date.toInt()))
//            Toast.makeText(this@Finish,"data added in dataBase",Toast.LENGTH_SHORT).show()
        }

    }

    private fun returnDate(): String? {
        val date12 = Calendar.getInstance()
        val dateTime = date12.time
        Log.d("", "" + dateTime)
        val asd = SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale.ENGLISH)
        return asd.format(dateTime)
    }


}