package com.example.train

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate

class diagrams : AppCompatActivity() {

    lateinit var barChart :  BarChart
    lateinit var textView : TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagrams)

        // Получаем имена стран
        var names_dia_1 = intent.getStringArrayListExtra("names_of_big")!!
        var names_dia_2 = intent.getStringArrayListExtra("names_of_random")!!
        // Получаем популяции стран
        var pop_dia_1 = intent.getIntegerArrayListExtra("population_of_big")!!
        var pop_dia_2 = intent.getIntegerArrayListExtra("population_of_random")!!

        barChart = findViewById(R.id.barChart)
        textView = findViewById(R.id.rating)


        var r_group : RadioGroup = findViewById(R.id.radioGroup)
        var r1 : RadioButton = findViewById(R.id.radioButton)
        var r2 : RadioButton = findViewById(R.id.radioButton2)

        // Задаем листенер, который в зависимости от изменения кнопки вызывает функцию рисования графика
        r_group.setOnCheckedChangeListener { group, checkedId ->
            if (r1.id == checkedId) setBarChart(pop_dia_1, names_dia_1) else setBarChart(pop_dia_2, names_dia_2)
        }

        // Выделяем первую кнопку по умолчанию
        r_group.check(R.id.radioButton)


    }
    private fun setBarChart(list: ArrayList<Int>, names: ArrayList<String>) {
        val entries: ArrayList<BarEntry> = ArrayList()
        for (i in 1..5){
            entries.add(BarEntry(i.toFloat(), list[i-1].toFloat()))
        }

        val barDataSet = BarDataSet(entries, "")
        barDataSet.setColor(ColorTemplate.getHoloBlue())


        val data = BarData(barDataSet)
        barChart.data = data

        // Удаляем ненужное
        barChart.axisLeft.setDrawGridLines(false)
        barChart.xAxis.setDrawGridLines(false)
        barChart.xAxis.setDrawAxisLine(false)
        barChart.xAxis.isEnabled = false
        barChart.axisLeft.isEnabled = false
        barChart.axisRight.isEnabled = false
        barChart.description.isEnabled = false
        barChart.legend.isEnabled = false

        //Устанавливаем "легенду"
        var leg = ""
        for(i in 0..4){
            leg += "${i+1}" + " - " + names[i] + "\n"
        }
        textView.setText(leg)

        //Устанавливаем размер текста

        //Рисуем график
        barChart.invalidate()
    }

    fun moveToFlags(v: View){
        var intent = Intent(this, Flags::class.java)
        startActivity(intent)
    }
}