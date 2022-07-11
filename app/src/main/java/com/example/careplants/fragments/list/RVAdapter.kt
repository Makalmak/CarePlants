package com.example.careplants.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.careplants.R
import com.example.careplants.model.Plant
import kotlinx.android.synthetic.main.item_plant.view.*

class RVAdapter(private val onItemClick: (Plant) -> Unit) : RecyclerView.Adapter<RVAdapter.RVViewHolder>() {
    private var plantsList = emptyList<Plant>()
    class RVViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        return RVViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_plant, parent, false))

    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
        val currentItem = plantsList[position]
        holder.itemView.tv_name.text = currentItem.name
        // ох еще и про картинку бы не забыть!)

        holder.itemView.plant_item.setOnClickListener(){
            val action = MyPlantsFragmentDirections.actionMyPlantsFragmentToPlantInfoFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return plantsList.size
    }
    fun setData(plant: List<Plant>) {
        this.plantsList = plant
        notifyDataSetChanged()
    }
}