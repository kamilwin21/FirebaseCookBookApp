package com.example.appforlogindata.MainActivity.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appforlogindata.MainActivity.Adapters.AdapterRecipes
import com.example.appforlogindata.MainActivity.Classes.Recipes
import com.example.appforlogindata.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_recipes.*
import kotlinx.android.synthetic.main.fragment_recipes_recycler_view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecipesRecyclerViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipesRecyclerViewFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        val args = this.arguments
        val inputData = args?.get("kategoria")

        var database = FirebaseDatabase.getInstance().getReference("Recipes").child(FirebaseAuth.getInstance().currentUser!!.uid)
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {   var pomocList: ArrayList<Recipes> = arrayListOf()
                    var recipesList: ArrayList<Recipes> = arrayListOf()
                    var recipe: Recipes?
                    for (n in snapshot.children)
                    {
                        recipe = n.getValue(Recipes::class.java)
                        pomocList.add(recipe!!)

                    }

                    for (r in pomocList)
                    {
                        if(r.kategoria == inputData)
                        {
                            recipesList.add(r)
                        }
                    }
                    RecyclerView_Recipes_RecipesFragment.layoutManager = LinearLayoutManager(activity!!.applicationContext)
                    RecyclerView_Recipes_RecipesFragment.adapter = AdapterRecipes(activity!!.applicationContext,recipesList)






                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })










    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipes_recycler_view, container, false)
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecipesRecyclerViewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecipesRecyclerViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}