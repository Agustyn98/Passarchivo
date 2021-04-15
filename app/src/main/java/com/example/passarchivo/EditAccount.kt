package com.example.passarchivo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class EditAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_account)

        setButtonSaveAccountListener()

        var name_intent = intent.getStringExtra("name")
        var email_intent = intent.getStringExtra("email")
        var username_intent = intent.getStringExtra("username")
        var password_intent = intent.getStringExtra("password")
        var note_intent = intent.getStringExtra("note")

        var editTextName : EditText = findViewById(R.id.editTextTextAccountName)
        var editTextEmail : EditText = findViewById(R.id.editTextAccountEmail)
        var editTextUsername : EditText = findViewById(R.id.editTextAccountUsername)
        var editTextPassword : EditText = findViewById(R.id.editTextAccountPassword)
        var editTextNote : EditText = findViewById(R.id.editTextAccountNote)
        editTextName.setText(name_intent)
        editTextEmail.setText(email_intent)
        editTextUsername.setText(username_intent)
        editTextPassword.setText(password_intent)
        editTextNote.setText(note_intent)

    }


    fun setButtonSaveAccountListener(){
        var button : Button = findViewById(R.id.buttonSaveAccount)
        button.setOnClickListener(View.OnClickListener {

            val name = findViewById<EditText>(R.id.editTextTextAccountName).text.toString()
            val email = findViewById<EditText>(R.id.editTextAccountEmail).text.toString()
            val username = findViewById<EditText>(R.id.editTextAccountUsername).text.toString()
            val password = findViewById<EditText>(R.id.editTextAccountPassword).text.toString()
            val note = findViewById<EditText>(R.id.editTextAccountNote).text.toString()


            val intent = Intent()
            intent.putExtra("name", name)
            intent.putExtra("email", email)
            intent.putExtra("username", username)
            intent.putExtra("password", password)
            intent.putExtra("note", note)
            setResult(Activity.RESULT_OK, intent)
            finish()

        })
    }

}