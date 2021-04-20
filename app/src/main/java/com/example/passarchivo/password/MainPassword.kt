package com.example.passarchivo.password

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.passarchivo.DBHandler
import com.example.passarchivo.MainActivity
import com.example.passarchivo.R

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
                finish()

            } else {
                Toast.makeText(this, "Wrong password", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        checkMainPass()
    }


    private fun getMainPass(): String {
        val db: DBHandler = DBHandler(this)
        val mainPass = db.getMainPass()
        return mainPass
    }

    private fun checkMainPass() {
        val mainPass = getMainPass()
        if (mainPass.isEmpty()) {
            val intent: Intent = Intent(this, CreateMainPassword::class.java)
            startActivity(intent)

        }
    }

}