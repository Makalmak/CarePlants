package com.example.careplants.fragments.info

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.careplants.R
import com.example.careplants.databinding.FragmentPlantInfoBinding
import com.example.careplants.fragments.list.MyPlantsFragment
import com.example.careplants.model.Plant
import kotlinx.android.synthetic.main.fragment_edit_info.view.*
import kotlinx.android.synthetic.main.fragment_plant_info.view.*
import kotlinx.android.synthetic.main.item_plant.*

class PlantInfoFragment : Fragment(R.layout.fragment_plant_info) {
//    private var _binding: FragmentPlantInfoBinding? = null
//    private val binding get() = _binding!!

    private val args by navArgs<PlantInfoFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)


        view.editButton.setOnClickListener {
            val currentItem = Plant(args.currentPlant.id,args.currentPlant.name,args.currentPlant.info,
                args.currentPlant.wat_interval, args.currentPlant.notif_time)
            val action = PlantInfoFragmentDirections.actionPlantInfoFragmentToEditInfoFragment(currentItem)
            findNavController().navigate(action) }

        view.returnButton.setOnClickListener {
            findNavController().navigate(R.id.action_plantInfoFragment_to_myPlantsFragment) }

//        val id_of_recived_plant  = requireArguments().getInt("Key") // здесь получили переданное id растения, в чью карточку переходим (по этому id вытаскиваем все данные)
//
//        val plant = readAllData

//        _binding = FragmentPlantInfoBinding.bind(view)
//        with(binding) {
//            tvNameInfo.text = plant.name
//                // ВЫТАЩИТЬ И ЗАПИХАТЬ В КАРТОЧКУ ВСЕ ОСТАЛЬНОЕ
//        }

        view.tv_name_info.text = args.currentPlant.name
        view.tv_intervalOfWatering.text = args.currentPlant.wat_interval
        view.tv_info.text = args.currentPlant.info




    }
}