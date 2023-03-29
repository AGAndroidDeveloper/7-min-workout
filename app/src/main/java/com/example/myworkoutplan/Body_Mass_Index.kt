package com.example.myworkoutplan
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.TextView
import com.example.myworkoutplan.databinding.ActivityBodyMassIndexBinding
import kotlin.math.ceil


class Body_Mass_Index : AppCompatActivity() {
    private var binding12: ActivityBodyMassIndexBinding? = null
    private var healthMessage: TextView? = null

    var a11 : Double? = null
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding12 = ActivityBodyMassIndexBinding.inflate(layoutInflater)
        setContentView(binding12?.root)
        binding12?.feetLayout?.visibility = View.INVISIBLE
        binding12?.inchLayout?.visibility = View.INVISIBLE
        setSupportActionBar(binding12?.toolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        supportActionBar?.title = "Bmi Calculator"
        binding12?.toolbar?.setNavigationOnClickListener {
            onBackPressed()
        }
        clickMe()

        binding12?.calculateBMI?.setOnClickListener{
           if (binding12?.radioButton1?.isChecked==true){

               val b1 =  binding12?.inputHeightText?.text.toString().toDoubleOrNull()
               val a = binding12?.inputWeight?.text.toString().toDoubleOrNull()
               if (b1 != null) {
                   if (a != null) {
                     val asp =   returnYourBmi(b1,a)
                       healthMessage(asp)


                   }
               }
               binding12?.inputHeightText?.text?.clear()
               binding12?.inputWeight?.text?.clear()
           }else{


               val b11 = binding12?.feetEdittext?.text.toString().toDoubleOrNull()?.times(12)
               val b12 = b11?.plus(binding12?.inchEdittext?.text.toString().toDoubleOrNull()!!)
               val b1 = b12?.times(0.0254)
               val w = binding12?.usWeightPound?.text.toString().toDoubleOrNull()?.times(0.453592)

               if (b1 != null) {
                   if (w != null) {
                      val asd =  returnYourBmi(b1,w)
                       healthMessage(asd)
                   }
               }
               binding12?.usWeightPound?.text?.clear()
               binding12?.feetEdittext?.text?.clear()
               binding12?.inchEdittext?.text?.clear()

           }




        }
        healthMessage = binding12?.hMessage

    }

    private fun healthMessage(asp: Double) {
        if (asp > 25 && asp < 29) {
            "you are overWeight, you should workout!!".also { healthMessage?.text = it }
        }
        if (asp < 25 && asp > 18.5) {
            healthMessage?.text = getString(R.string.message1)
        }
        if (asp <= 18.5) {
            healthMessage?.text = getString(R.string.hello1)
        }
        if (asp > 29) {
            healthMessage?.text =
                getString(R.string.message3)
        }
    }
    private fun clickMe() {
        binding12?.radioButton1?.setOnCheckedChangeListener { _: CompoundButton, _: Boolean ->
            if (binding12?.radioButton1?.isChecked == true) {
                binding12?.feetLayout?.visibility = View.INVISIBLE
                binding12?.inchLayout?.visibility = View.INVISIBLE
                binding12?.inputHeightLayout?.visibility = View.VISIBLE
                binding12?.usWeightLayout?.visibility = View.GONE


               // b1 = binding12?.inputHeightText?.text.toString().toDoubleOrNull()

            } else {
                binding12?.feetLayout?.visibility = View.VISIBLE
                binding12?.inchLayout?.visibility = View.VISIBLE
                binding12?.inputHeightLayout?.visibility = View.INVISIBLE
                binding12?.usWeightLayout?.visibility = View.VISIBLE
                binding12?.hMessage?.visibility = View.VISIBLE

            }
        }
    }

    @SuppressLint("SetTextI18n")
       private fun returnYourBmi(b1 :Double,a:Double) :Double{
        val height = b1.times(b1)
        val bmiIndex = ceil(a.div(height))
        binding12?.bmiResult?.text = "Your Bmi is :${bmiIndex}"











return bmiIndex
    }



}
