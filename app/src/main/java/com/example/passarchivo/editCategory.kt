package com.example.passarchivo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

class editCategory : AppCompatActivity() , AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_category)
        val id = intent.getIntExtra("id",0)
        val name = intent.getStringExtra("name")
        val imageId = intent.getIntExtra("imageId",0)
        println("Objeto con id : "  +id+ "     name   "  + name  + " imageid  "  + imageId)
        setSpinnerImageId()

    }

    fun setSpinnerImageId(){

        var spinner : Spinner = findViewById(R.id.spinnerImageId)
        var listImgeIds : ArrayList<String> = ArrayList<String>()
        listImgeIds.add("Cart"); listImgeIds.add("Laptop"); listImgeIds.add("Kyoko");
       // var dataAdapter : ArrayAdapter<Int> = ArrayAdapter<Int>(this,R.layout.activity_edit_category, listImgeIds)
        var dataAdapter : ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item , listImgeIds)

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = dataAdapter

        spinner.onItemSelectedListener = this

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        var selectedItem = parent?.selectedItem
        Toast.makeText(this,"Selected: " + selectedItem, Toast.LENGTH_SHORT).show()

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //?
    }

}

