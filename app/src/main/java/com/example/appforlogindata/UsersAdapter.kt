package com.example.appforlogindata

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.users_recycler_view.view.*

class UsersAdapter(val context:Context,val usersList: List<User>): RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val positionList = inflater.inflate(R.layout.users_recycler_view,parent,false)
        return MyViewHolder(positionList)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val  email = holder.myview.textView_mainActivity
        val name = holder.myview.textView_mainActivity1
        val UserRecyclerView = holder.myview.UserRecyclerView

        email.text = usersList[holder.adapterPosition].email
        name.text = usersList[holder.adapterPosition].name

        UserRecyclerView.setOnClickListener{
           // Toast.makeText(holder.myview.context.applicationContext,
           // "Clicked: "+ holder.adapterPosition,Toast.LENGTH_SHORT).show()

            val intent = Intent(holder.myview.context.applicationContext,
                                UserProfileActivity::class.java)
            intent.putExtra("UserUId", usersList[holder.adapterPosition].id)
            intent.putExtra("UserEmail", usersList[holder.adapterPosition].email)
            intent.putExtra("UserName", usersList[holder.adapterPosition].name)
            intent.putExtra("UserAge", usersList[holder.adapterPosition].age)

            holder.myview.context.startActivity(intent)


        }


    }


    override fun getItemCount(): Int {
        return usersList.size
    }


}

class MyViewHolder(val myview: View):RecyclerView.ViewHolder(myview){}