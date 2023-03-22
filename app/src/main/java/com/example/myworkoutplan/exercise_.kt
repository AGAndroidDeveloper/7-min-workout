package com.example.myworkoutplan

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myworkoutplan.databinding.ActivityExerciseBinding
import com.example.myworkoutplan.databinding.CustomDialogBinding

import java.util.*
import kotlin.collections.ArrayList

class exercise_ : AppCompatActivity(),TextToSpeech.OnInitListener {
    private var restTimer: CountDownTimer? = null

    private var myDialog :Dialog? = null

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseTimerDuration: Long = 5

    // The Variable for the exercise list and current position of exercise here it is -1 as the list starting element is 0
    // START
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
           showDialog()
           // onBackPressed()
        }

        tts = TextToSpeech(this, this)
        exerciseList = Constants.defaultExerciseList()
        // END
        setupRestView()
        setupExerciseStatusRecyclerView()
    }





    //Setting up the Get Ready View with 10 seconds of timer
    //START

    private fun setupRestView() {

        player?.stop()
//
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



        // Setting the upcoming exercise name in the UI element
        // START
        // Here we have set the upcoming exercise name to the text view
        // Here as the current position is -1 by default so to selected from the list it should be 0 so we have increased it by +1.
        binding?.readyUpNextExerciseName?.text = exerciseList!![currentExercisePosition + 1].getName()

        speakOut(binding?.readyUpNextExerciseName?.text as String)


        // This function is used to set the progress details.
        setRestProgressBar()
    }
    // END
    // Setting up the 10 seconds timer for rest view and updating it continuously.
    //START

    private fun setRestProgressBar() {


        // Here we have started a timer of 10 seconds so the 10000 is milliseconds is 10 seconds and the countdown interval is 1 second so it 1000.
        restTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(p0: Long) {
                // restProgress++ // It is increased by 1
                val a = p0 / 1000
                binding?.readyProgressBar?.progress = a.toInt() // Indicates progress bar progress
                binding?.readyTimer?.text =
                    a.toString()  // Current progress is set to text view in terms of seconds.
                binding?.readyProgressBar?.max = 10
            }

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
    //END


    // Setting up the Exercise View with a 30 seconds timer
    // START

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
        speakSound()
        setExerciseProgressBar()

    }

    private fun setExerciseProgressBar() {

        // binding?.progressBarExercise?.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(exerciseTimerDuration * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // exerciseProgress++
                binding?.exerciseProgressbar?.progress = (millisUntilFinished / 1000).toInt()
                binding?.exerciseTimer?.text = (millisUntilFinished / 1000).toString()

            }
            override fun onFinish() {
                // TODO(Step 2 : We have changed the status of the selected item and updated the status of that, so that the position is set as completed in the exercise list.)
                // START
                exerciseList!![currentExercisePosition].setIsSelected(false) // exercise is completed so selection is set to false
                exerciseList!![currentExercisePosition].setIsCompleted(true) // updating in the list that this exercise is completed
                exerciseAdapter!!.notifyDataSetChanged() // Notifying the adapter class.
                // END
                // Updating the view after completing the 30 seconds exercise
                // START
                if (currentExercisePosition < exerciseList?.size!! - 1) {

                    setupRestView()
                } else {
                    val intent = Intent(this@exercise_,Finish::class.java)
                    startActivity(intent)
                             finish()

//                    Toast.makeText(
//                        this@exercise_,
//                        "Congratulations! You have completed the 7 minutes workout.",
//                        Toast.LENGTH_SHORT
//                    ).show()
                }
                // END
            }
        }.start()

    }
    // END


    // Destroying the timer when closing the activity or app
    //START
   
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
    }
       override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts?.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            }

        } else {
            Log.e("TTS", "Initialization Failed!")
        }

    }

    private fun speakOut(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    private fun setupExerciseStatusRecyclerView() {
        binding?.itemRecycle?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!)
        binding?.itemRecycle?.adapter = exerciseAdapter
    }
    @SuppressLint("SuspiciousIndentation")
    private fun speakSound(){
        player = MediaPlayer.create(this,R.raw.music)

      player?.isLooping = false
        player?.start()

        player?.isLooping = false

    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        showDialog()
//        super.onBackPressed()
    }

    private fun showDialog(){
        myDialog = Dialog(this)
       val dialogBinding = CustomDialogBinding.inflate(LayoutInflater.from(this))
           myDialog?.setContentView(dialogBinding.root)
        dialogBinding.btnYes.setOnClickListener {
            finish()
        }
        dialogBinding.btnNo.setOnClickListener {
            myDialog?.cancel()
        }

        myDialog?.setCancelable(false)
        myDialog?.setCanceledOnTouchOutside(false)
        myDialog?.create()
            myDialog?.show()
    }

}



