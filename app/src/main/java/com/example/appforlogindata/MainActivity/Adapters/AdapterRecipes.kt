package com.example.appforlogindata.MainActivity.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appforlogindata.MainActivity.Classes.Recipes
import com.example.appforlogindata.R
import kotlinx.android.synthetic.main.position_list_recyclerview_recipes.view.*

class AdapterRecipes(val context: Context, val recipesList: ArrayList<Recipes>): RecyclerView.Adapter<MviewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MviewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val positionList = inflater.inflate(R.layout.position_list_recyclerview_recipes, parent,false)
        return MviewHolder(positionList)
    }

    override fun onBindViewHolder(holder: MviewHolder, position: Int) {
       val name =  holder.view.tw_positionList_RecyclerView_Recipes
        name.text = recipesList[holder.adapterPosition].nazwa
    }

    override fun getItemCount(): Int {
        return recipesList.size
    }


}


class MviewHolder(val view: View): RecyclerView.ViewHolder(view){}