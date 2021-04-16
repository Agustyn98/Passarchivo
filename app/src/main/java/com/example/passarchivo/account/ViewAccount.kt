package com.example.passarchivo.account

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.passarchivo.DBHandler
import com.example.passarchivo.R

class ViewAccount : AppCompatActivity() {
    var id = -1
    var name: String? = ""
    var email: String? = ""
    var username: String? = ""
    var password: String? = ""
    var note: String? = ""
    var idCategory: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_account)

        id = intent.getIntExtra("id", -1)

        updateVariables()

        setButtonEditListener()
        setButtonDeleteListener()

    }

    fun updateVariables() {

        var db: DBHandler = DBHandler(this)
        var account = db.getOneAccount(id)
        name = account?.getName()
        email = account?.getEmail()
        username = account?.getUserName()
        password = account?.getPassword()
        note = account?.getNote()
        if (account != null) {
            idCategory = account.getIdCategory()
        }

        var textViewName = findViewById<TextView>(R.id.textView_ViewAccountName)
        var textViewEmail = findViewById<TextView>(R.id.textView_ViewAccountEmail)
        var textViewUsername = findViewById<TextView>(R.id.textView_ViewAccountUsername)
        var textViewPassword = findViewById<TextView>(R.id.textView_ViewAccountPassword)
        var textViewNote = findViewById<TextView>(R.id.textView_ViewAccountNote)

        textViewName.setText(name)
        textViewEmail.setText(email)
        textViewUsername.setText(username)
        textViewPassword.setText(password)
        textViewNote.setText(note)
    }

    fun setButtonDeleteListener() {
        val buttonDelete = findViewById<Button>(R.id.buttonDeleteAccount)
        buttonDelete.setOnClickListener(View.OnClickListener {

            val dialogBuilder = AlertDialog.Builder(this)

            // set message of alert dialog
            dialogBuilder.setMessage("Are you sure you want to delete '$name' ?")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("Proceed", DialogInterface.OnClickListener { dialog, idDialog ->
                    var db: DBHandler = DBHandler(this)
                    db.deleteOneAccount(id)

                    finish()
                })
                // negative button text and action
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, idDialog ->
                    dialog.cancel()
                })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("Confirmation")
            // show alert dialog
            alert.show()


            /*
            var db: DBHandler = DBHandler(this)
            db.deleteOneAccount(id)

            finish()

             */


        })
    }

    private val EDIT_ACCOUNT_CODE = 3;

    fun setButtonEditListener() {

        var buttonEdit: Button = findViewById(R.id.buttonEditAccount)
        buttonEdit.setOnClickListener(View.OnClickListener {


            val intent: Intent = Intent(this, EditAccount::class.java).apply {
                putExtra("name", name)
                putExtra("email", email)
                putExtra("username", username)
                putExtra("password", password)
                putExtra("note", note)

            }
            startActivityForResult(intent, EDIT_ACCOUNT_CODE)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == EDIT_ACCOUNT_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                val return_name = data!!.getStringExtra("name").toString()
                val return_email = data!!.getStringExtra("email").toString()
                val return_username = data!!.getStringExtra("username").toString()
                val return_password = data!!.getStringExtra("password").toString()
                val return_note = data!!.getStringExtra("note").toString()


                val account = Account(
                    id,
                    return_name,
                    return_email,
                    return_username,
                    return_password,
                    return_note,
                    idCategory
                )

                var db: DBHandler = DBHandler(this)
                val result = db.updateOneAccount(account)
                updateVariables()
            }
        }
    }

}