package com.example.passarchivo

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

    var flag = true;
    fun buttonAddListener() {
        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        buttonAdd.setOnClickListener(View.OnClickListener {
            Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
            //codigo aca
            var category1: Category = Category(1, "nombre", 2)
            if (flag) {
                flag = false;
            } else {
                category1 = Category(2, "Flag = false", 1)
                flag = true;
            }

            var db: DBCategory = DBCategory(this@MainActivity)

            val result = db.addOne(category1)
            setRecycleViewAdapter()

        })
    }


}