package com.example.careplants

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_info.*
import kotlinx.android.synthetic.main.fragment_plant_info.*
import kotlinx.android.synthetic.main.fragment_plant_info.toolbar

class AddInfoFragment : Fragment (R.layout.fragment_add_info){
    var intervals =
        arrayOf("Не выбрано", "Каждый день", "Каждые 2 дня", "Раз в неделю", "Раз в две недели")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val spinner = view?.findViewById<Spinner>(R.id.spinner)
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        val adapter: ArrayAdapter<String?> =
            ArrayAdapter<String?>(requireContext(), android.R.layout.simple_spinner_item, intervals)
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Применяем адаптер к элементу spinner
        spinner!!.adapter = adapter
        val itemSelectedListener: AdapterView.OnItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    // Получаем выбранный объект
                    val item = parent.getItemAtPosition(position) as String
                    // Здесь можно как-то использовать этот item, сохранять его в бд или переносить куда-либо
                    tv_intervalOfWatering.text = item // я его, например, засовываю в карточку растения
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        spinner.onItemSelectedListener = itemSelectedListener
    }
}