package com.example.passarchivo.account

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.passarchivo.DBHandler
import com.example.passarchivo.R
import com.google.android.material.switchmaterial.SwitchMaterial

class AccountListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_list)


        setButtonAddListener()
        setSwitchListener()
    }




    override fun onResume() {
        super.onResume()

        setRecycleViewAccounts()

    }

    private fun setRecycleViewAccounts(sortByNewest : Boolean = true) {
        val idCategory = intent.getIntExtra("id", -1)

        val recycleViewAccounts: RecyclerView = findViewById(R.id.recyclerViewSearchedAccounts)

        val array: ArrayList<Account> =
            if (idCategory > 0)
                getAllAccountsById(sortByNewest = sortByNewest)
            else
                getAllAccounts(sortByNewest = sortByNewest)

        recycleViewAccounts.adapter = AdapterAccount(array)
        recycleViewAccounts.layoutManager = LinearLayoutManager(this)

        val textView : TextView = findViewById(R.id.textViewNoItemsAccount)
        if(array.size <= 0 )
            textView.visibility = View.VISIBLE
        else
            textView.visibility = View.GONE
    }

    private fun getAllAccountsById(sortByNewest : Boolean = true): ArrayList<Account> {

        val idCategory = intent.getIntExtra("id", -1)
        val db: DBHandler = DBHandler(this@AccountListActivity)
        return db.getAllAccountsById(idCategory, sortByNewest = sortByNewest)
    }

    private fun getAllAccounts(sortByNewest : Boolean = true): ArrayList<Account> {
        val db: DBHandler = DBHandler(this@AccountListActivity)
        return db.getAllAccounts(sortByNewest = sortByNewest)
    }

    private var ACTIVITY_ADD_ACCOUNT_REQUEST_CODE = 3

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

    private fun setSwitchListener() {
        val switch : SwitchMaterial = findViewById(R.id.switchSortByAccount)
        switch.setOnCheckedChangeListener{ buttonView, isChecked ->
            if(isChecked){
                setRecycleViewAccounts(sortByNewest = true)
            }else{
                setRecycleViewAccounts(sortByNewest = false)
            }
        }
    }

    // This method is called after coming back from adding an account
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
                val db: DBHandler = DBHandler(this@AccountListActivity)
                db.addOneAccount(account)
                setRecycleViewAccounts()

            }
        }

    }

}