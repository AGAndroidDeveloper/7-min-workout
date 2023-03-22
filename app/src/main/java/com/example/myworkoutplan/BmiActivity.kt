package com.example.myworkoutplan

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.myworkoutplan.databinding.ActivityBmiBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.math.ceil

class BmiActivity : AppCompatActivity() {

    private var binding :ActivityBmiBinding? = null
    private var healthMessage:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbar)    // this will set toolbar at the top of activity

        if (supportActionBar!=null){
           supportActionBar!!.setDisplayHomeAsUpEnabled(true)   // by clicking on this user can go to previous activity

            }



        binding?.toolbar?.setNavigationOnClickListener{
            onBackPressed()
        }



        binding?.button2?.setOnClickListener {
            try {
                if (binding?.inputHeight?.text?.isNotEmpty() == true){
                    if (binding?.inputWeight?.text?.isNotEmpty() == true){
                        returnYourBmi()
                       // binding?.inputHeight?.text =
                    }
                }
                else{
                    binding?.textView4?.let { it1 -> Snackbar.make(it1,"please enter right details",Snackbar.LENGTH_SHORT).show() }
                }
            }catch(e :Exception){
                throw   e
            }
        }
        healthMessage = binding?.textView5

    }
    @SuppressLint("SetTextI18n")
    private fun returnYourBmi() {
      val   a = binding?.inputWeight?.text.toString().toDoubleOrNull()
        val make = binding?.inputHeight?.text
     val    b1  = make.toString().toDoubleOrNull()
        val height = b1?.times(b1)
        val bmiIndex = a?.div(height!!)?.toInt()
        binding?.textView3?.text = "Your Bmi is :${bmiIndex?.let { ceil(it.toDouble()).toString() }}"


        //
        //BMI Categories:
        //Underweight = <18.5
        //Normal weight = 18.5–24.9
        //Overweight = 25–29.9
        //Obesity = BMI of 30 or greater
        healthMessage?.visibility = View.VISIBLE
        if (bmiIndex != null) {
            if (bmiIndex in 26..29){
                healthMessage?.text = "you are overWeight, you should workout!!"
            }
            if ((bmiIndex < 25) && (bmiIndex > 18.5)){
                healthMessage?.text = "you are normal weight , you should not be worry about."
            }
            if (bmiIndex<=18.5){
                healthMessage?.text = "you are underweight weight , you should  be worry about."
            }
            if (bmiIndex<25){
                healthMessage?.text = "you are normal weight , you should not be worry about."
            }
            if (bmiIndex>29){
                healthMessage?.text = "you are in a serious situation right now, you really need to loose some weight "
            }



        }
    }
}