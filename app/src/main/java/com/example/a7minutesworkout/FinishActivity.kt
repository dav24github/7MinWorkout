package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {
    lateinit var toolBarFinishActivity: androidx.appcompat.widget.Toolbar
    lateinit var finishBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        toolBarFinishActivity = findViewById(R.id.toolbar_finish_activity)
        setSupportActionBar(toolBarFinishActivity)
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)

        finishBtn = findViewById(R.id.btnFinish)

        toolBarFinishActivity.setNavigationOnClickListener {
            onBackPressed()
        }

        finishBtn.setOnClickListener {
            finish(  )
        }

        addDateToDatabase()
    }

    private fun addDateToDatabase(){
        val calendar = Calendar.getInstance()
        val dateTime = calendar.time
        Log.i("DATE", " $dateTime")

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)

        val dbHandler = SqliteOpenHelper(this)
        dbHandler.addDate(date)

        Log.i("DATE: ", " Added")
    }
}