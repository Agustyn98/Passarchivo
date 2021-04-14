package com.example.passarchivo

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat.startActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView


class AdapterCategory(private val dataSet: ArrayList<Category>) : RecyclerView.Adapter<AdapterCategory.ViewHolder>() {

    /*
        ViewHolder: Aca tengo las "views" donde van mis variables, donde se guarda cada item del RecycleList
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: CardView
        val textView: TextView
        val imageView: ImageView


        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.category_name)
            imageView = view.findViewById(R.id.category_image)
            cardView = view.findViewById(R.id.cardview_category)
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterCategory.ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterCategory.ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the contents of the view with that element

        holder.textView.text = dataSet[position].getName()
        when (dataSet[position].getImageId()) {
            0 -> holder.imageView.setImageResource(R.drawable.ic_laptop1)
            1 -> holder.imageView.setImageResource(R.drawable.ic_shopping_cart)
            2 -> holder.imageView.setImageResource(R.drawable.ic_credit_card)
            3 -> holder.imageView.setImageResource(R.drawable.ic_email)
            4 -> holder.imageView.setImageResource(R.drawable.ic_money)
            5 -> holder.imageView.setImageResource(R.drawable.ic_web)
            6 -> holder.imageView.setImageResource(R.drawable.ic_baseline_android_24)
            7 -> holder.imageView.setImageResource(R.mipmap.ic_kyoko)
            else -> {
                holder.imageView.setImageResource(R.drawable.ic_baseline_android_24)
            }
        }


        holder.cardView.setOnClickListener(View.OnClickListener {
            println("Click en textView ! \n" + dataSet[position].getId() + " <-- id?")
            val intent = Intent(holder.textView.context, AccountListActivity::class.java)
            startActivity(holder.textView.context, intent, null)
        })

        //LONG CLICK listener
        holder.cardView.setOnLongClickListener(View.OnLongClickListener {

            var context = holder.textView.context;
            AlertDialog.Builder(context)
                .setTitle("Options")
                .setMessage("message .....")
                .setIcon(android.R.drawable.btn_default)
                    //CANCEL BUTTON
                .setPositiveButton("CANCEL",
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()

                    })
                    //EDIT BUTTON
                .setNeutralButton("EDIT" , DialogInterface.OnClickListener { dialog, id ->

                    val ACTIVITY_EDIT_REQUEST_CODE = 2;
                    val intent: Intent = Intent(context, editCategory::class.java).apply {
                        putExtra("id", dataSet[position].getId() )
                        putExtra("name", dataSet[position].getName() )
                        putExtra("imageId", dataSet[position].getImageId() )
                    }
                    var mainActivityInstance1 = context;
                    mainActivityInstance1 = mainActivityInstance1 as (MainActivity)
                    startActivityForResult(mainActivityInstance1, intent, ACTIVITY_EDIT_REQUEST_CODE, null)

                    dialog.cancel()
                })
                    //DELETE BUTTON
                .setNegativeButton("DELETE", DialogInterface.OnClickListener { dialog, id ->
                    var context = holder.textView.context;

                    val builder2 = AlertDialog.Builder(context)
                    builder2.setTitle("Are you sure?")
                    builder2.setMessage("All password that belong to this category will be deleted")
                    builder2.setPositiveButton("Yes") { dialog, which ->
                        //
                        println("Delete \n")
                        var mainActivityInstance = context;
                        mainActivityInstance = mainActivityInstance as (MainActivity) //Explicit Cast
                        var db: DBCategory = DBCategory(context)
                        val id = dataSet[position].getId();
                        val result = db.deleteOne(id)
                        mainActivityInstance.setRecycleViewAdapter();
                        Toast.makeText(context, "Deleted.", Toast.LENGTH_SHORT).show()
                        dialog.cancel()
                    }
                    builder2.setNegativeButton("Cancel") { dialog, which ->
                        //
                        dialog.cancel()
                    }
                    builder2.show()
                }).show()


            true
        })

    }

    override fun getItemCount() = dataSet.size // lo mismo que { return dataSet.size }

    //TODO:
    // Category CRUD: Select: done, Delete: done
    //                Add: pending interface, Edit: pending interface

}