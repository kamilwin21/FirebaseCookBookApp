package com.example.appforlogindata.MainActivity.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.appforlogindata.MainActivity.AddRecipeActivity
import com.example.appforlogindata.MainActivity.Classes.Recipes
import com.example.appforlogindata.R
import kotlinx.android.synthetic.main.position_list_recyclerview_recipes.view.*

class AdapterRecipes(val context: Context?, val recipesList: ArrayList<Recipes>): RecyclerView.Adapter<MviewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MviewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val positionList = inflater.inflate(R.layout.position_list_recyclerview_recipes, parent,false)
        return MviewHolder(positionList)
    }

    override fun onBindViewHolder(holder: MviewHolder, position: Int) {
        val name =  holder.view.tw_positionList_RecyclerView_Recipes
        val LinearLayout = holder.view.idLinearLayoutToAdapterShowRecipes

        name.text = recipesList[holder.adapterPosition].nazwa

        LinearLayout.setOnClickListener {

            //Wyświetl dany przepis

        }

        holder.view.imageviewRecycleViewRecipes.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                var popupMenu: PopupMenu = PopupMenu(holder.view.context, holder.view.imageviewRecycleViewRecipes)
                popupMenu.inflate(R.menu.popup_menu_categories)
                popupMenu.setOnMenuItemClickListener{
                        item ->
                    if(item?.itemId == R.id.menu_edit_categories)
                    {
                        val intent = Intent(holder.view.context.applicationContext, AddRecipeActivity::class.java)
                        intent.putExtra("idR", recipesList[holder.adapterPosition].id)
                        intent.putExtra("uidR", recipesList[holder.adapterPosition].uid)
                        intent.putExtra("nazwaR", recipesList[holder.adapterPosition].nazwa)
                        intent.putExtra("kategoriaR", recipesList[holder.adapterPosition].kategoria)
                        intent.putExtra("opisR", recipesList[holder.adapterPosition].opis)
                        holder.view.context.startActivity(intent)

                    }else if(item?.itemId == R.id.menu_delete_categories)
                    {


                    }
                    true

                }
                popupMenu.show()

            }


        })



    }

    override fun getItemCount(): Int {
        return recipesList.size
    }


}


class MviewHolder(val view: View): RecyclerView.ViewHolder(view){}