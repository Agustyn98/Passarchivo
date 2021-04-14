package com.example.passarchivo

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        buttonAddListener()
        setRecycleViewAdapter()
    }


    fun setRecycleViewAdapter() {

        val recyclerViewCategories: RecyclerView = findViewById(R.id.listViewCategories)
        recyclerViewCategories.layoutManager = LinearLayoutManager(this)
        //create array from DB
        var array1 = getAllCategories()
        recyclerViewCategories.adapter = AdapterCategory(array1)

    }

    fun getAllCategories(): ArrayList<Category> {
        var db: DBCategory = DBCategory(this@MainActivity)

        return db.getAll()
    }


    fun deleteCategory(id: Int) {


        setRecycleViewAdapter()
    }


    private var ACTIVITY_ADD_REQUEST_CODE = 1
    private var ACTIVITY_EDIT_REQUEST_CODE = 2
    fun buttonAddListener() {
        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        buttonAdd.setOnClickListener(View.OnClickListener {
            //Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
            val intent: Intent = Intent(this, editCategory::class.java)
            startActivityForResult(intent, ACTIVITY_ADD_REQUEST_CODE)

        })
    }

    // This method is called when the second activity finishes
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        println("BACK TO MAIN")
        if (ACTIVITY_ADD_REQUEST_CODE == requestCode) {
            if (resultCode == Activity.RESULT_OK) {
                val returnId = data!!.getIntExtra("id",-1)
                val returnName = data!!.getStringExtra("categoryName")
                val returnImageId = data!!.getIntExtra("categoryImageId", -1)
                var db: DBCategory = DBCategory(this@MainActivity)
                var category : Category = Category(name = returnName.toString(), imageId = returnImageId)
                val result = db.addOne(category)
                setRecycleViewAdapter()

            }
        }else if(ACTIVITY_EDIT_REQUEST_CODE == requestCode){
            if(resultCode == Activity.RESULT_OK){

                val returnName = data!!.getStringExtra("categoryName")
                val returnId = data!!.getIntExtra("id", -1)
                val returnImageId = data!!.getIntExtra("categoryImageId", -1)

                println("HLOOLA::   "  + returnName + " id: " + returnId + " image ID " + returnImageId)

                var db: DBCategory = DBCategory(this)
                val category : Category = Category(returnId, returnName.toString() , returnImageId)
                val result = db.updateOne(category)
                setRecycleViewAdapter()

            }
        }
    }



}