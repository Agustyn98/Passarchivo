package com.example.passarchivo.account

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.passarchivo.DBHandler
import com.example.passarchivo.R
import com.example.passarchivo.category.Category
import kotlin.collections.ArrayList

class EditAccount : AppCompatActivity() , AdapterView.OnItemSelectedListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_account)

        setButtonSaveAccountListener()

        val name_intent = intent.getStringExtra("name")
        val email_intent = intent.getStringExtra("email")
        val username_intent = intent.getStringExtra("username")
        val password_intent = intent.getStringExtra("password")
        val note_intent = intent.getStringExtra("note")
        val customName_intent = intent.getStringExtra("customFieldName")
        val customValue_intent = intent.getStringExtra("customFieldValue")

        val editTextName : EditText = findViewById(R.id.editTextTextAccountName)
        val editTextEmail : EditText = findViewById(R.id.editTextAccountEmail)
        val editTextUsername : EditText = findViewById(R.id.editTextAccountUsername)
        val editTextPassword : EditText = findViewById(R.id.editTextAccountPassword)
        val editTextNote : EditText = findViewById(R.id.editTextAccountNote)
        val editTextCustomName : EditText = findViewById(R.id.editTextAccountCustomName)
        val editTextCustomValue : EditText = findViewById(R.id.editTextAccountCustomValue)


        editTextName.setText(name_intent)
        editTextEmail.setText(email_intent)
        editTextUsername.setText(username_intent)
        editTextPassword.setText(password_intent)
        editTextNote.setText(note_intent)
        editTextCustomName.setText(customName_intent)
        editTextCustomValue.setText(customValue_intent)
        populateSpinner()

    }

    private fun populateSpinner() {
        val db: DBHandler = DBHandler(this)

        val array = db.getAllCategories()
        array.add(0, Category(name = "--No Category--" , imageId = 1, id = -1))

        val spinnerCategory = findViewById<Spinner>(R.id.spinnerEditCategory)
        spinnerCategory.onItemSelectedListener = this

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, array)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = adapter

        selectCurrentCategory(array,spinnerCategory)
    }

    private fun selectCurrentCategory(arrayList: ArrayList<Category>, spinner: Spinner){

        val idCategory_intent = intent.getIntExtra("idCategory" , -1)

        for (i in 0..arrayList.size){
            val cat : Category = spinner.getItemAtPosition(i) as Category
            if(cat.getId() == idCategory_intent){
                spinner.setSelection(i)
                break
            }
        }
    }



    private fun setButtonSaveAccountListener(){
        val button : Button = findViewById(R.id.buttonSaveAccount)
        button.setOnClickListener(View.OnClickListener {

            val name = findViewById<EditText>(R.id.editTextTextAccountName).text.toString()
            val email = findViewById<EditText>(R.id.editTextAccountEmail).text.toString()
            val username = findViewById<EditText>(R.id.editTextAccountUsername).text.toString()
            val password = findViewById<EditText>(R.id.editTextAccountPassword).text.toString()
            val note = findViewById<EditText>(R.id.editTextAccountNote).text.toString()
            val customName = findViewById<EditText>(R.id.editTextAccountCustomName).text.toString()
            val customValue =
                findViewById<EditText>(R.id.editTextAccountCustomValue).text.toString()


            val intent = Intent()
            intent.putExtra("name", name)
            intent.putExtra("email", email)
            intent.putExtra("username", username)
            intent.putExtra("password", password)
            intent.putExtra("note", note)
            intent.putExtra("customFieldName", customName)
            intent.putExtra("customFieldValue", customValue)
            intent.putExtra("idCategory", idCategory_selected)
            setResult(Activity.RESULT_OK, intent)
            finish()

        })
    }

    fun getSpinnerItemSelected(){
        val spinner = findViewById<Spinner>(R.id.spinnerEditCategory)
        val pos = spinner.selectedItemPosition
        val cat : Category = spinner.getItemAtPosition(pos) as Category
        idCategory_selected = cat.getId()
    }

    private var idCategory_selected = -1
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val cat : Category = parent?.getItemAtPosition(position) as Category
        idCategory_selected = cat.getId()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

}