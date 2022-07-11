package com.example.careplants.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.careplants.R
import com.example.careplants.model.Plant
import com.example.careplants.viewmodel.PlantViewModel

import kotlinx.android.synthetic.main.fragment_add_info.*
import kotlinx.android.synthetic.main.fragment_add_info.view.*

class AddInfoFragment : Fragment(R.layout.fragment_add_info) {
    var intervals =
        arrayOf("Не выбрано", "Каждый день", "Каждые 2 дня", "Раз в неделю", "Раз в две недели")

    private lateinit var mPlantViewModel: PlantViewModel

    lateinit var intervalWat : String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val spinner = view.findViewById<Spinner>(R.id.spinner)
        println(spinner)
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
                    intervalWat = parent.getItemAtPosition(position) as String
                    // Здесь можно как-то использовать этот item, сохранять его в бд или переносить куда-либо (но я так понимаю нужен биндинг)

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        spinner.onItemSelectedListener = itemSelectedListener

        //val view = inflater.inflate(R.layout.fragment_add_info, container, false)

        mPlantViewModel = ViewModelProvider(this)[PlantViewModel::class.java]

        view.doneButton.setOnClickListener {
            insertDataToDatabase()
        }
        view.return_in_add_Button.setOnClickListener {
            findNavController().navigate(R.id.action_addInfoFragment_to_myPlantsFragment)
        }
    }

    private fun insertDataToDatabase() {
        val name = add_name.text.toString()
        val time = editTextTime.text.toString()
        val info = add_info.text.toString()
        val interv = intervalWat

        if (inputCheck(name)) {
            val plant = Plant(0, name, info, interv, time)

            mPlantViewModel.addPlant(plant)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addInfoFragment_to_myPlantsFragment)

        } else {
            Toast.makeText(requireContext(), "Please fill out name field!", Toast.LENGTH_LONG)
                .show()
        }

    }

    private fun inputCheck(name: String): Boolean {
        return !(TextUtils.isEmpty(name))
    }
}