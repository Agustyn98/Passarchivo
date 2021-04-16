package com.example.passarchivo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.passarchivo.category.CategoryList
import com.example.passarchivo.category.EditCategory

class MainPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_password)

        setButtonEnterMainPassListener()

    }

    private fun setButtonEnterMainPassListener() {
        val buttonEnter: Button = findViewById(R.id.buttonEnterMainPass)
        buttonEnter.setOnClickListener(View.OnClickListener {

            val enteredPass = findViewById<EditText>(R.id.editTextMainPassword).text.toString()
            val db: DBHandler = DBHandler(this)
            val realPass = db.getMainPass()
            if (enteredPass.equals(realPass, false)) {
                //correct password
                val intent: Intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            } else {
                //wrong password

                println("wrong pass")
            }
        })
    }

    override fun onResume() {
        super.onResume()
        checkMainPass()
    }


    fun getMainPass(): String {
        val db: DBHandler = DBHandler(this)
        val mainPass = db.getMainPass()
        return mainPass
    }

    fun checkMainPass() {
        val mainPass = getMainPass()
        if (mainPass.isNullOrEmpty()) {
            val intent: Intent = Intent(this, CreateMainPassword::class.java)
            startActivity(intent)
        }
    }

}