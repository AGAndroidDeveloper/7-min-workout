package com.example.myworkoutplan

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.text.Editable
import com.example.myworkoutplan.databinding.ActivityBmiBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.math.ceil

class BmiActivity : AppCompatActivity() {

    private var binding :ActivityBmiBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)




        binding?.button2?.setOnClickListener {
            try {
                if (binding?.inputHeight?.text?.isNotEmpty() == true){
                    if (binding?.inputWeight?.text?.isNotEmpty() == true){
                        returnYourBmi()
                    }
                }
                else{
                    binding?.textView4?.let { it1 -> Snackbar.make(it1,"please enter right details",Snackbar.LENGTH_SHORT).show() }
                }
            }catch(e :Exception){
                throw   e
            }



//
            //
            //
            //            }

        }
    }
    private fun returnYourBmi() {
        // here we get height in meter
      val   a = binding?.inputWeight?.text.toString().toDoubleOrNull()
        val make = binding?.inputHeight?.text
     val    b1  = make.toString().toDoubleOrNull()

        val height = b1?.times(b1)


        val bmiIndex = a?.div(height!!)
        binding?.textView3?.text = "Your Bmi is :${bmiIndex?.let { ceil(it).toString() }}"
    }
}