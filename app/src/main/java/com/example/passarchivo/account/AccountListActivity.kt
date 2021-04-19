package com.example.passarchivo.account

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.passarchivo.DBHandler
import com.example.passarchivo.R

class AccountListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_list)


        setButtonAddListener()
    }


    override fun onResume() {
        super.onResume()

        setRecycleViewAccounts()

    }

    fun setRecycleViewAccounts() {
        val idCategory = intent.getIntExtra("id", -1)

        val recycleViewAccounts: RecyclerView = findViewById(R.id.recyclerViewSearchedAccounts)

        var array = ArrayList<Account>()
        if (idCategory > 0)
            array = getAllAccountsById()
        else
            array = getAllAccounts()

        recycleViewAccounts.adapter = AdapterAccount(array)
        recycleViewAccounts.layoutManager = LinearLayoutManager(this)
    }

    fun getAllAccountsById(): ArrayList<Account> {
        val idCategory = intent.getIntExtra("id", -1)

        var array: ArrayList<Account> = ArrayList<Account>()
        val db: DBHandler = DBHandler(this@AccountListActivity)
        return db.getAllAccountsById(idCategory)
    }

    fun getAllAccounts(): ArrayList<Account> {
        var array: ArrayList<Account> = ArrayList<Account>()
        val db: DBHandler = DBHandler(this@AccountListActivity)
        return db.getAllAccounts()
    }

    var ACTIVITY_ADD_ACCOUNT_REQUEST_CODE = 3;

    fun setButtonAddListener() {
        val buttonAdd: Button = findViewById(R.id.buttonAddAccountSearch)

        buttonAdd.setOnClickListener(View.OnClickListener {

            val idCategory = intent.getIntExtra("id", -1)
            val intent: Intent = Intent(this, EditAccount::class.java).apply {
                putExtra("idCategory", idCategory)
            }
            startActivityForResult(intent, ACTIVITY_ADD_ACCOUNT_REQUEST_CODE)

        })
    }

    // This method is called after coming back from adding an account
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ACTIVITY_ADD_ACCOUNT_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                val idCategory = intent.getIntExtra("id", -1)

                val name = data?.getStringExtra("name")!!
                val email = data?.getStringExtra("email")!!
                val username = data?.getStringExtra("username")!!
                val password = data?.getStringExtra("password")!!
                val note = data?.getStringExtra("note")!!
                val customFieldName = data?.getStringExtra("customFieldName")!!
                val customFieldValue = data?.getStringExtra("customFieldValue")!!

                val account: Account = Account(
                    name = name,
                    email = email,
                    username = username,
                    password = password,
                    note = note,
                    idCategory = idCategory,
                    customFieldName = customFieldName,
                    customFieldValue = customFieldValue
                )
                var db: DBHandler = DBHandler(this@AccountListActivity)
                var result = db.addOneAccount(account)
                setRecycleViewAccounts()

            }
        }

    }

}