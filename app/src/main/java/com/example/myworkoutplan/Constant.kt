package com.example.myworkoutplan

import java.util.ArrayList

class Constants {
    companion object {

        fun defaultExerciseList(): ArrayList<ExerciseModel> {

            val exerciseList = ArrayList<ExerciseModel>()

            val jumpingJacks =
                ExerciseModel(1, "plank", R.drawable.download__13_, false, false)
            exerciseList.add(jumpingJacks)

            val wallSit = ExerciseModel(2, "Squat", R.drawable.download__14_, false, false)
            exerciseList.add(wallSit)

            val pushUp = ExerciseModel(3, "Lunges", R.drawable.download__15_, false, false)
            exerciseList.add(pushUp)

//            val abdominalCrunch =
//                ExerciseModel(4, "Abdominal Crunch", R.drawable.abnominal_crunches, false, false)
//            exerciseList.add(abdominalCrunch)
//
//            val stepUpOnChair =
//                ExerciseModel(
//                    5,
//                    "Step-Up onto Chair",
//                    R.drawable.download__19_,
//                    false,
//                    false
//                )
//            exerciseList.add(stepUpOnChair)
//
//            val squat = ExerciseModel(6, "Squat", R.drawable.download__6_, false, false)
//            exerciseList.add(squat)
//
//            val tricepDipOnChair =
//                ExerciseModel(
//                    7,
//                    "Tricep Dip On Chair",
//                    R.drawable.tricep_on_chair,
//                    false,
//                    false
//                )
//            exerciseList.add(tricepDipOnChair)
//
//            val plank = ExerciseModel(8, "Plank", R.drawable.download__15_, false, false)
//            exerciseList.add(plank)
//
//            val highKneesRunningInPlace =
//                ExerciseModel(
//                    9, "High Knees Running In Place",
//                    R.drawable.high_knee_running,
//                    false,
//                    false
//                )
//            exerciseList.add(highKneesRunningInPlace)
//
//            val lunges = ExerciseModel(10, "Lunges", R.drawable.download__14_, false, false)
//            exerciseList.add(lunges)
//
//            val pushupAndRotation =
//                ExerciseModel(
//                    11,
//                    "Push up and Rotation",
//                    R.drawable.pushup_rotation,
//                    false,
//                    false
//                )
//            exerciseList.add(pushupAndRotation)
//
//            val sidePlank = ExerciseModel(12, "Side Plank", R.drawable.download__17_, false, false)
//            exerciseList.add(sidePlank)


           return exerciseList
//        }
        }
    }
}
