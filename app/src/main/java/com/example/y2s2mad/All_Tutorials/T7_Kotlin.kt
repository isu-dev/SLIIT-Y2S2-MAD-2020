package com.example.y2s2mad.All_Tutorials

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.y2s2mad.R
import java.util.*

class T7_Kotlin : AppCompatActivity() {

    //lateinit var birthyear : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_t7__kotlin)

        //var birthyear = findViewById<EditText>(R.id.t7_et_year)

        //val ageInYears = findViewById<TextView>(R.id.t7_tv_value_year)
    }

    fun onSubmit(view: View) {
        val currentyear = Calendar.getInstance().get(Calendar.YEAR)

        Log.i("current year", currentyear.toString())

        var birthyear = Integer.parseInt(findViewById<EditText>(R.id.t7_et_year).toString())

        Log.i("birth year", birthyear.toString())

        var age = currentyear - birthyear;

        Log.i("age", age.toString())

        var ageInYears = findViewById<TextView>(R.id.t7_tv_value_year)

        ageInYears.setText(age)
    }
}