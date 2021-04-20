package com.example.passarchivo.search


import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.passarchivo.DBHandler
import com.example.passarchivo.R
import com.example.passarchivo.account.Account
import com.example.passarchivo.account.EditAccount

class SearchAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seach_account)
        setButtonAddListener()
        setEditTextSearchListener()
    }

    override fun onResume() {
        super.onResume()
        this.setRecycleViewAccounts()

    }


    private fun setRecycleViewAccounts() {

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewSearchedAccounts)
        val searchString = findViewById<EditText>(R.id.editTextSearchAccountBox).text.toString()

        val array = ArrayList<Account>()
        val adapter = AdapterSearch(array)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        searchAccounts(searchString, recyclerView)

    }

    private fun searchAccounts(search: String, recyclerView: RecyclerView) {

        if (search.isBlank())
            return
        val db: DBHandler = DBHandler(this)
        val array = db.searchAccounts(search)
        recyclerView.invalidate()
        recyclerView.adapter = AdapterSearch(array)
        // adapter.notifyDataSetChanged()

    }

    private fun setEditTextSearchListener() {

        val editText: EditText = findViewById(R.id.editTextSearchAccountBox)
        editText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->

            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                setRecycleViewAccounts()
            } else if (keyCode == KeyEvent.KEYCODE_BACK) {
                finish()
            }
            true
        })
    }

    private val ACTIVITY_ADD_ACCOUNT_REQUEST_CODE = 5
    private fun setButtonAddListener() {
        val buttonAdd: Button = findViewById(R.id.buttonAddAccountSearch)
        buttonAdd.setOnClickListener(View.OnClickListener {

            val idCategory = intent.getIntExtra("id", -1)
            val intent: Intent = Intent(this, EditAccount::class.java).apply {
                putExtra("idCategory", idCategory)
            }
            startActivityForResult(intent, ACTIVITY_ADD_ACCOUNT_REQUEST_CODE)

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ACTIVITY_ADD_ACCOUNT_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                //val idCategory = intent.getIntExtra("id", -1)
                val idCategory = data?.getIntExtra("idCategory", -1)!!
                //intent = data sent from previous activity
                //data = data sent from activity initiated after this one
                val name = data?.getStringExtra("name")!!
                val email = data.getStringExtra("email")!!
                val username = data.getStringExtra("username")!!
                val password = data.getStringExtra("password")!!
                val note = data.getStringExtra("note")!!
                val customFieldName = data.getStringExtra("customFieldName")!!
                val customFieldValue = data.getStringExtra("customFieldValue")!!

                val account = Account(
                    name = name,
                    email = email,
                    username = username,
                    password = password,
                    note = note,
                    idCategory = idCategory,
                    customFieldName = customFieldName,
                    customFieldValue = customFieldValue
                )
                val db: DBHandler = DBHandler(this)
                db.addOneAccount(account)
                setRecycleViewAccounts()

            }
        }

    }

}