package com.example.careplants.fragments.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.careplants.R
import com.example.careplants.viewmodel.PlantViewModel
import kotlinx.android.synthetic.main.fragment_my_plants.view.*

class MyPlantsFragment : Fragment(R.layout.fragment_my_plants) {
    private lateinit var mPlantViewModel: PlantViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recyclerview
        val adapter = RVAdapter()
        {
//            val plantBundle = Bundle()
//            plantBundle.putInt("Key", it.id) // здесь передаем id растения, на карточку которого сейчас переходим  !!!!
//            findNavController().navigate(
//                R.id.action_myPlantsFragment_to_plantInfoFragment,
//                plantBundle
//            )
        }
        val recyclerView = view.rv_plants
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //adapter.setData(PlantRepository2.plantiklist)

        // UserViewModel
        mPlantViewModel = ViewModelProvider(this)[PlantViewModel::class.java]
        mPlantViewModel.readAllData.observe(viewLifecycleOwner, Observer { plant ->
            adapter.setData(plant) })


        view.floatind_add_button.setOnClickListener {
            findNavController().navigate(R.id.action_myPlantsFragment_to_addInfoFragment)
        }
    }
}