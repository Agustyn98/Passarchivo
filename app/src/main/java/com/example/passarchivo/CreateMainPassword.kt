package com.example.passarchivo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class CreateMainPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_main_password)

        buttonCreateMainPassListener()
    }

    private fun buttonCreateMainPassListener() {

        val button : Button = findViewById(R.id.buttonCreateMainPass)
        button.setOnClickListener(View.OnClickListener {
            val pass1 = findViewById<EditText>(R.id.editTextCreateMainPass1).text.toString()
            val pass2 = findViewById<EditText>(R.id.editTextCreateMainPass2).text.toString()

            if( !(pass1.isNullOrEmpty() || pass2.isNullOrEmpty()) ){
                if(pass1.equals(pass2,false)){

                    registerMainPass(pass1)
                    goBackToMain()

                }
            }
        })

    }

    fun registerMainPass(pass: String) : Long{
        val db: DBHandler = DBHandler(this)
        val result = db.addMainPass(pass)
        return result
    }

    fun goBackToMain(){
        val intent = Intent()
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}