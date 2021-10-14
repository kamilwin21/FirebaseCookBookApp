package com.example.appforlogindata

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.appforlogindata.MainActivity.AddCategoryActivity
import com.example.appforlogindata.MainActivity.Classes.CategoryRecipes
import com.example.appforlogindata.MainActivity.RecipesActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.position_recycler_view_recipes_fragment.view.*


class AdapterRecyclerView_RecipesFragment(val context: Context, val categoriesList: ArrayList<CategoryRecipes>):RecyclerView.Adapter<MyViewHolderRecipesFragment>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderRecipesFragment {
        val inflater1 = LayoutInflater.from(parent.context)
        val positionList1 = inflater1.inflate(R.layout.position_recycler_view_recipes_fragment,parent,false)
        return MyViewHolderRecipesFragment(positionList1)
    }

    override fun onBindViewHolder(holder: MyViewHolderRecipesFragment, position: Int) {

            val title = holder.view.titleRecipesCategory
            val LinearLayout = holder.view.idLinearLayoutToAdapterRecipes

            title.text = categoriesList[holder.adapterPosition].nazwaC
            LinearLayout.setOnClickListener {


                val intent = Intent(holder.view.context.applicationContext, RecipesActivity::class.java)
                intent.putExtra("kategoria",categoriesList[holder.adapterPosition].nazwaC)
                holder.view.context.startActivity(intent)

            }



            holder.view.imageViewCategories.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v: View?) {

                    var popupMenu: PopupMenu = PopupMenu(holder.view.context,holder.view.imageViewCategories)
                    popupMenu.inflate(R.menu.popup_menu_categories)
                    popupMenu.setOnMenuItemClickListener { item ->
                        if (item?.itemId == R.id.menu_edit_categories) {
                            //Do something if Edytuj

                            val intent = Intent(holder.view.context, AddCategoryActivity::class.java)
                            intent.putExtra("CategoryIdC", categoriesList[holder.adapterPosition].idC)
                            intent.putExtra("CategoryName", categoriesList[holder.adapterPosition].nazwaC)
                            intent.putExtra("CategoryUIdC", categoriesList[holder.adapterPosition].uidC)
                            holder.view.context.startActivity(intent)

                        } else if (item?.itemId == R.id.menu_delete_categories) {
                            //Usuwanie kategorii z RecyclerView
                            val database = FirebaseDatabase.getInstance().getReference("Categories")
                            database!!.child(FirebaseAuth.getInstance().currentUser!!.uid)
                                .child(categoriesList[holder.adapterPosition].idC).removeValue()
                            categoriesList.removeAll(categoriesList)


                        }
                        true
                    }
                    popupMenu.show()



                }


            })

    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }


}

class MyViewHolderRecipesFragment(val view: View):RecyclerView.ViewHolder(view){
}