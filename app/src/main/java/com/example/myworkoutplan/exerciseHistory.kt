package com.example.myworkoutplan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myworkoutplan.databinding.ActivityExerciseHistoryBinding
import kotlinx.coroutines.launch

class exerciseHistory : AppCompatActivity() {
    private var binding: ActivityExerciseHistoryBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExerciseHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarDisplayExercise)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        supportActionBar?.title = "History"
        binding?.toolbarDisplayExercise?.setNavigationOnClickListener {
            onBackPressed()
        }


        val dao = (application as ExerciseApplication).database.eDao()

fetchDataToUi(dao)
    }


    fun fetchDataToUi(dao: ExerciseDao) {

        lifecycleScope.launch {
            dao.fetchAllExerciseList().collect { // return list

if (it.isNotEmpty()){

    val dateTobeShown = ArrayList<ExerciseLIst>()

    val arrayList = it as ArrayList<ExerciseLIst>
    for (i in arrayList){
        dateTobeShown.add(ExerciseLIst(i.id,i.date))
           }
    val setAdapter = Adapter(dateTobeShown)
    binding?.recyclerViewDisplayExercise?.adapter = setAdapter
    binding?.recyclerViewDisplayExercise?.layoutManager = LinearLayoutManager(this@exerciseHistory)
}else{

    Toast.makeText(this@exerciseHistory,"no data here to be shown ",Toast.LENGTH_SHORT).show()
}

            }


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}
