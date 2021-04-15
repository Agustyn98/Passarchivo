package com.example.passarchivo

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.RecyclerView

class AdapterAccount(private val dataSet: ArrayList<Account>) :
    RecyclerView.Adapter<AdapterAccount.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.textViewAccountName)
        }
    }

    // Create a new view, which defines the UI (.xml) of the list item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterAccount.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.account_layout, parent, false)
        return AdapterAccount.ViewHolder(view)
    }

    //This method iterates for each item
    override fun onBindViewHolder(holder: AdapterAccount.ViewHolder, position: Int) {
        holder.textView.setText(dataSet[position].getName());

        holder.textView.setOnClickListener(View.OnClickListener {

            val intent = Intent(holder.textView.context, ViewAccount::class.java).apply {
                putExtra("id", dataSet[position].getId())
                putExtra("name", dataSet[position].getName())
                putExtra("email", dataSet[position].getEmail())
                putExtra("username", dataSet[position].getUserName())
                putExtra("password", dataSet[position].getPassword())
                putExtra("note", dataSet[position].getNote())
                putExtra("idCategory", dataSet[position].getIdCategory())

            }

            startActivity(holder.textView.context , intent, null)


        })
    }

    override fun getItemCount(): Int {
        return dataSet.size;
    }

}