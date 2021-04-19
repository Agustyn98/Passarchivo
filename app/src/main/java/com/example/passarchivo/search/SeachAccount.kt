package com.example.passarchivo.search


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.passarchivo.DBHandler
import com.example.passarchivo.R
import com.example.passarchivo.account.Account


class SeachAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seach_account)

        setEditTextSearchListener()
    }

    override fun onResume() {
        super.onResume()
        setRecycleViewAccounts()


    }


    private fun setRecycleViewAccounts() {

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewSearchedAccounts)
        val searchString = findViewById<EditText>(R.id.editTextSearchAccountBox).text.toString()

        var array = ArrayList<Account>()
        recyclerView.adapter = AdapterSearch(array)
        recyclerView.layoutManager = LinearLayoutManager(this)


        searchAccounts(searchString,recyclerView)


    }

    fun searchAccounts(search : String , recyclerView: RecyclerView){

        if (search.isNullOrEmpty())
            return
        val db: DBHandler = DBHandler(this)
        val array =  db.searchAccounts(search)
        println("AAAAAAAAAAAAH")
        recyclerView.invalidate()
        recyclerView.adapter = AdapterSearch(array)

    }

    private fun setEditTextSearchListener() {

        val editText: EditText = findViewById(R.id.editTextSearchAccountBox)
        editText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->

            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                setRecycleViewAccounts()
                true
            }
            false
        })
    }
}