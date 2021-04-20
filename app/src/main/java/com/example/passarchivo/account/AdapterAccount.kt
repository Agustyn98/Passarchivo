package com.example.passarchivo.account

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.passarchivo.R

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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.account_layout, parent, false)
        return ViewHolder(view)
    }

    //This method iterates for each item
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = dataSet[position].getName()

        if(position % 2 == 0){
            holder.textView.setBackgroundColor(Color.parseColor("#EBEDEC"))
        }else{
            holder.textView.setBackgroundColor(Color.parseColor("#ffffff"))
        }

        holder.textView.setOnClickListener(View.OnClickListener {

            val intent = Intent(holder.textView.context, ViewAccount::class.java).apply {
                putExtra("id", dataSet[position].getId())
            }

            startActivity(holder.textView.context, intent, null)

        })
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}