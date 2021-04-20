package com.example.passarchivo.password

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.passarchivo.DBHandler
import com.example.passarchivo.R

class ChangePassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        setButtonChangePassListener()
    }

    private fun setButtonChangePassListener() {

        val button: Button = findViewById(R.id.buttonChangeMainPass)
        button.setOnClickListener(View.OnClickListener {

            val newCurrentPass =
                findViewById<EditText>(R.id.editTextCurrentMainPass).text.toString()
            val newPass1 = findViewById<EditText>(R.id.editTextNewMainPass1).text.toString()
            val newPass2 = findViewById<EditText>(R.id.editTextNewMainPass2).text.toString()

            val confirmation = confirmNewPass(newCurrentPass, newPass1, newPass2)
            Toast.makeText(this, confirmation, Toast.LENGTH_LONG).show()

            if (confirmation.equals("Password changed", true)) {
                button.isEnabled = false
            }

        })
    }

    private fun confirmNewPass(newCurrentPass: String, newPass1: String, newPass2: String): String {
        val db: DBHandler = DBHandler(this)
        val currentPass = db.getMainPass()
        println("old pass: $currentPass , written: $newCurrentPass")
        println("new pass1 and 2:  $newPass1   $newPass2")

        if (newPass1.isBlank()) {
            return "Error"
        }

        if (currentPass.isNotBlank()) {
            if (currentPass.equals(newCurrentPass, false)) {
                if (newPass1.equals(newPass2, false)) {

                    db.updateMainPass(newPass1)
                    return "Password changed"
                }
                return "Password don't match"
            }
            return "Wrong password"
        }
        return "Error"
    }
}