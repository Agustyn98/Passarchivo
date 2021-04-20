package com.example.passarchivo.password

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.passarchivo.DBHandler
import com.example.passarchivo.R

class CreateMainPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_main_password)

        buttonCreateMainPassListener()
    }

    private fun buttonCreateMainPassListener() {

        val button: Button = findViewById(R.id.buttonCreateMainPass)
        button.setOnClickListener(View.OnClickListener {
            val pass1 = findViewById<EditText>(R.id.editTextCreateMainPass1).text.toString()
            val pass2 = findViewById<EditText>(R.id.editTextCreateMainPass2).text.toString()

            if (pass1.isNotBlank()) {
                if (pass1.equals(pass2, false)) {

                    registerMainPass(pass1)
                    goBackToMain()

                }else{
                    Toast.makeText(this,"Passwords don't match" , Toast.LENGTH_SHORT)
                }
            }else{
                Toast.makeText(this,"Password can't be blank",Toast.LENGTH_SHORT)
            }
        })

    }

    private fun registerMainPass(pass: String): Long {
        val db: DBHandler = DBHandler(this)
        val result = db.addMainPass(pass)
        return result
    }

    private fun goBackToMain() {
        val intent = Intent()
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}