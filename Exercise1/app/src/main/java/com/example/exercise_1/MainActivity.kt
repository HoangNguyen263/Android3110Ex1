package com.example.exercise_1

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.selects.select

class MainActivity : AppCompatActivity() {

    lateinit var input: EditText
    lateinit var bgroup : RadioGroup
    lateinit var submit : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        submit = findViewById(R.id.button)

        var numberList = ArrayList<Int>()

        bgroup = findViewById<RadioGroup>(R.id.radioGroup)



        val items: ArrayList<Int> = numberList
        val adapter : ArrayAdapter<Int> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            numberList
        )

        val listView: ListView = findViewById(R.id.list_item)
        listView.adapter = adapter

        submit.setOnClickListener {
            numberList.clear()
            var value:Int = 0
            input = findViewById(R.id.editText)
            var isInt = input.text.toString().matches(Regex("^-?\\d+$"))
            if (isInt) {
                value = input.text.toString().toInt()
                Toast.makeText(applicationContext,"Du lieu hop le",Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext,"Du lieu khong hop le",Toast.LENGTH_LONG).show()
            }
            var selectedBtt = bgroup.checkedRadioButtonId
            when (selectedBtt) {
                R.id.evenBtt -> {
                    for (i in 0..value) {
                        if (i % 2 == 0) numberList.add(i)
                    }
                }

                R.id.oddBtt -> {
                    for (i in 1..value) {
                        if (i % 2 != 0) numberList.add(i)
                    }
                }

                R.id.squareBtt -> {
                    var i = 0
                    while (i * i <= value) {
                        numberList.add(i * i)
                        i++
                    }
                }
            }
            Log.d("MainActivity", "Value:$value $numberList")
            adapter.notifyDataSetChanged()
        }

    }
}