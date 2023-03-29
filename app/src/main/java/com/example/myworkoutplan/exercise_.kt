package com.example.myworkoutplan

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.QUEUE_FLUSH
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myworkoutplan.databinding.ActivityExerciseBinding
import com.example.myworkoutplan.databinding.CustomDialogBinding

import java.util.*
import kotlin.collections.ArrayList

   class Exercise : AppCompatActivity() {
    private var restTimer: CountDownTimer? = null
    private var myDialog :Dialog? = null
    private var exerciseTimer: CountDownTimer? = null
    private var exerciseTimerDuration: Long = 30
private var relexTimer :Long? = 10
    val cmpleteExerciseList : ArrayList<String> = ArrayList()

       private var exerciseList: ArrayList<ExerciseModel>? = null // We will initialize the list later.
    private var currentExercisePosition = -1 // Current Position of Exercise.
    private var binding: ActivityExerciseBinding? = null
    private var tts: TextToSpeech? = null // Variable for Text to Speech
    private var player: MediaPlayer? = null
    private var exerciseAdapter: ExerciseStatusAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        setSupportActionBar(binding?.textviewActionbar)

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.textviewActionbar?.setNavigationOnClickListener {
         //  showDialog()
            onBackPressed()
        }

        exerciseList = Constants.defaultExerciseList()
        // END
        setupRestView()
        setupExerciseStatusRecyclerView()

    }

       private fun Texttospeech(a :String) {
           tts = TextToSpeech(this) { status ->
               if (status == TextToSpeech.SUCCESS) {
                   Log.i("TTS", "$status+successful")
                   tts?.language = Locale.US
                   tts?.speak(a, QUEUE_FLUSH, null, "")
               }

           }
       }

       @SuppressLint("SuspiciousIndentation")
       private fun setupRestView() {






        binding?.readyFrameLayout?.visibility = View.VISIBLE
        binding?.readyTimer?.visibility = View.VISIBLE
        binding?.textView2?.visibility = View.VISIBLE
        binding?.readyUpNextExerciseName?.visibility = View.VISIBLE
        binding?.frameLayout?.visibility = View.INVISIBLE
        binding?.exerciseName?.visibility = View.INVISIBLE
        binding?.image?.visibility = View.INVISIBLE

        if (restTimer != null) {
            restTimer?.cancel()
            // restProgress = 0
        }
//
        if (player!=null){
            player?.stop()
        }

           setRestProgressBar()
        binding?.readyUpNextExerciseName?.text = exerciseList!![currentExercisePosition + 1].getName()

        val a :String = exerciseList!![currentExercisePosition + 1].getName()
           Texttospeech(a)

    }


    private fun setRestProgressBar() {

        restTimer = object : CountDownTimer(relexTimer!!*1000, 1000) {
            override fun onTick(p0: Long) {
                // restProgress++ // It is increased by 1
                val a = p0 / 1000
                binding?.readyProgressBar?.progress = a.toInt() // Indicates progress bar progress
                binding?.readyTimer?.text =
                    a.toString()  // Current progress is set to text view in terms of seconds.
                binding?.readyProgressBar?.max = relexTimer?.toInt()!!

            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onFinish() {
                // When the 10 seconds will complete this will be executed.
                currentExercisePosition++
                exerciseList!![currentExercisePosition].setIsSelected(true) // Current Item is selected
                exerciseAdapter!!.notifyDataSetChanged() // Notified the current item to adapter class to reflect it into UI.
                // END
                setupExerciseView()
            }
        }.start()
    }

    private fun setupExerciseView() {
        binding?.frameLayout?.visibility = View.VISIBLE
        binding?.exerciseTimer?.visibility = View.VISIBLE
        binding?.exerciseName?.visibility = View.VISIBLE
        // binding?.upcomingLabel?.visibility = View.INVISIBLE
        //   binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.readyFrameLayout?.visibility = View.INVISIBLE
        binding?.image?.visibility = View.VISIBLE
binding?.textView2?.visibility = View.INVISIBLE
        binding?.readyUpNextExerciseName?.visibility = View.INVISIBLE
        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            // exerciseProgress = 0
        }
        // speakOut(exerciseList!![currentExercisePosition].getName())

        binding?.image?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.exerciseName?.text = exerciseList!![currentExercisePosition].getName()
        try {
          //  val SoundUri = Uri.parse("android.resource://com.example.myworkoutplan"+R.raw.music)
            player = MediaPlayer.create(applicationContext,R.raw.music)
            player?.isLooping = false
            player?.start()
        }catch (e :java.lang.Exception){
            e.printStackTrace()
        }

        setExerciseProgressBar()

    }

    private fun setExerciseProgressBar() {


        exerciseTimer = object : CountDownTimer(exerciseTimerDuration * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // exerciseProgress++

                binding?.exerciseProgressbar?.progress = (millisUntilFinished / 1000).toInt()
                binding?.exerciseProgressbar?.max = exerciseTimerDuration.toInt()
                binding?.exerciseTimer?.text = (millisUntilFinished / 1000).toString()

            }
            @SuppressLint("NotifyDataSetChanged")
            override fun onFinish() {
                 exerciseList!![currentExercisePosition].setIsSelected(false) // exercise is completed so selection is set to false
                exerciseList!![currentExercisePosition].setIsCompleted(true)
                cmpleteExerciseList.add(exerciseList!![currentExercisePosition].getName())
                exerciseAdapter!!.notifyDataSetChanged() // Notifying the adapter class.
                if (currentExercisePosition < exerciseList?.size!! - 1) {
                tts!!.shutdown()
                    player?.stop()
                    setupRestView()
                } else {
                    player?.stop()
                    val intent = Intent(this@Exercise,Finish::class.java)
                    //intent.putExtra("EXERCISE-LIST",cmpleteExerciseList)
                    startActivity(intent)
                    finish()
//
//
                }
                // END
            }
        }.start()

    }

    public override fun onDestroy() {
        if (restTimer != null) {
            restTimer?.cancel()
            // restProgress = 0
        }
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        if (player != null) {
            player!!.stop()
        }
        super.onDestroy()
        binding = null

        if(myDialog!=null){
            myDialog?.cancel()
        }
    }

//       override fun onInit(status: Int) {
//        if (status == TextToSpeech.SUCCESS) {
//            // set US English as language for tts
//            val result = tts?.setLanguage(Locale.US)
//
//            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
//                Log.e("TTS", "The Language specified is not supported!")
//            }
//
//        } else {
//            Log.e("TTS", "Initialization Failed!")
//        }
//
//    }



    private fun setupExerciseStatusRecyclerView() {
        binding?.itemRecycle?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!)
        binding?.itemRecycle?.adapter = exerciseAdapter
    }
   
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        showDialog()
//        super.onBackPressed()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun showDialog(){
        myDialog = Dialog(this)
       val dialogBinding = CustomDialogBinding.inflate(LayoutInflater.from(this@Exercise))
           myDialog?.setContentView(dialogBinding.root)
        dialogBinding.btnYes.setOnClickListener {
            finish()
            myDialog?.cancel()
        }
        dialogBinding.btnNo.setOnClickListener {
            myDialog?.cancel()
        }

        myDialog?.setCanceledOnTouchOutside(false)
        myDialog?.create()
            myDialog?.show()
    }

}



