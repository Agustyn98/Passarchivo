package com.example.passarchivo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class editCategory : AppCompatActivity() , AdapterView.OnItemSelectedListener {

    var id = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_category)

        val name = intent.getStringExtra("name")
        val imageId = intent.getIntExtra("imageId",0)
        id = intent.getIntExtra("id",0)

        setSpinnerImageId()
        setSaveButtonListener()

        println("Objeto con id : "  +id+ "     name   "  + name  + " imageid  "  + imageId)
        var editText : EditText = findViewById(R.id.editTextCategoryName)
        editText.setText(name)
        var spinner : Spinner = findViewById(R.id.spinnerImageId)
        spinner.setSelection(imageId)

    }

    fun setSpinnerImageId(){

        var spinner : Spinner = findViewById(R.id.spinnerImageId)
        var listImgeIds : ArrayList<String> = ArrayList<String>()
        listImgeIds.add("Laptop"); listImgeIds.add("Shopping Cart"); listImgeIds.add("Credit Card"); listImgeIds.add("Email");
        listImgeIds.add("Money");listImgeIds.add("Web");listImgeIds.add("Android");listImgeIds.add("Kyoko");
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

    fun setSaveButtonListener(){
        var buttonSave : Button = findViewById(R.id.buttonSave)
        buttonSave.setOnClickListener(View.OnClickListener {
            val editTextName = findViewById(R.id.editTextCategoryName) as EditText
            val categoryName = editTextName.text.toString()
            val spinner: Spinner = findViewById(R.id.spinnerImageId)
            val categoryImageId = spinner.selectedItemPosition

            // Put the String to pass back into an Intent and close this activity
            val intent = Intent()
            intent.putExtra("id", id)
            intent.putExtra("categoryName", categoryName)
            intent.putExtra("categoryImageId" , categoryImageId)
            setResult(Activity.RESULT_OK, intent)
            finish()

        })
    }

}

