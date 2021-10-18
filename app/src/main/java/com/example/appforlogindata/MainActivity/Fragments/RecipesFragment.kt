package com.example.appforlogindata.MainActivity.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appforlogindata.*
import com.example.appforlogindata.MainActivity.AddCategoryActivity
import com.example.appforlogindata.MainActivity.Classes.CategoryRecipes
import com.example.appforlogindata.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_recipes.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecipesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipesFragment : Fragment() {

    private lateinit var database: DatabaseReference

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Dodanie itemmenu do fragmentu
        setHasOptionsMenu(true)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onStart() {

        //Tworzenie listy do pzrechowywania kategorii
        var categoriesList: ArrayList<CategoryRecipes> = ArrayList()

        var ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("Categories").child(FirebaseAuth.getInstance().currentUser!!.uid)
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot!!.exists())
                {
                    for (h in snapshot.children){
                        val category = h.getValue(CategoryRecipes::class.java)
                        categoriesList.add(category!!)
                        recyclerView_recipesFragment.layoutManager = GridLayoutManager(context,3)
                        recyclerView_recipesFragment.adapter = AdapterRecyclerView_RecipesFragment(requireContext(),categoriesList)

                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })




        super.onStart()
    }



    override fun onResume() {



        super.onResume()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_recipes_category,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        if(item?.itemId == R.id.add_category)
        {
            val intent = Intent(context, AddCategoryActivity::class.java)
            startActivity(intent)
           /*
            val addCategoryFragment = AddCategoryFragment()
            val cfm = childFragmentManager

                cfm.beginTransaction().add(R.id.linear_fragment_add_category, addCategoryFragment ).commit()
                Toast.makeText(context,"Dodaj kategorię", Toast.LENGTH_SHORT).show()

            */
        }

        return super.onOptionsItemSelected(item)
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecipesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecipesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}