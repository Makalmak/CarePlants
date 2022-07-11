package com.example.careplants.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.careplants.R
import com.example.careplants.model.Plant
import com.example.careplants.viewmodel.PlantViewModel
import kotlinx.android.synthetic.main.fragment_add_info.view.*
import kotlinx.android.synthetic.main.fragment_edit_info.*
import kotlinx.android.synthetic.main.fragment_edit_info.view.*

class EditInfoFragment : Fragment(R.layout.fragment_edit_info) {

    private val args by navArgs<EditInfoFragmentArgs>()

    private lateinit var mPlantViewModel: PlantViewModel

    lateinit var intervalWat : String

    var intervals =
        arrayOf("Не выбрано", "Каждый день", "Каждые 2 дня", "Раз в неделю", "Раз в две недели")


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

        mPlantViewModel = ViewModelProvider(this)[PlantViewModel::class.java]

        view.add_name_edit.setText(args.currentPlant.name)
        view.edit_info.setText(args.currentPlant.info)
        view.edit_Text_Time.setText(args.currentPlant.notif_time)
        intervalWat = args.currentPlant.wat_interval

//        view.return_in_edit_Button.setOnClickListener {
//            findNavController().navigate(R.id.action_editInfoFragment_to_plantInfoFragment)
//        }

        view.done_Button_edit.setOnClickListener {
            updateItem()
        }

        view.button.setOnClickListener() {
            deletePlant()
        }


    }
    private fun updateItem() {
        val name = add_name_edit.text.toString()
        val info = edit_info.text.toString()
        val time = edit_Text_Time.text.toString()
        val wat_interv = intervalWat

        if (inputCheck(name)){
            val updatedPlant = Plant(args.currentPlant.id, name, info, wat_interv, time)

            mPlantViewModel.updatePlant(updatedPlant)
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_editInfoFragment_to_myPlantsFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out name field!", Toast.LENGTH_LONG)

        }

    }

    private fun inputCheck(name: String): Boolean {
        return !(TextUtils.isEmpty(name))
    }

    private fun deletePlant(){val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            mPlantViewModel.deletePlant(args.currentPlant)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentPlant.name}!",
                Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_editInfoFragment_to_myPlantsFragment)
        }
        builder.setNegativeButton("No"){_ , _ ->

        }
        builder.setTitle("Delete ${args.currentPlant.name}?")
        builder.setMessage("Are you sure you want to delete ${args.currentPlant.name}?")
        builder.create().show()
    }
}




