package com.example.passarchivo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import com.example.passarchivo.account.AccountListActivity
import com.example.passarchivo.category.CategoryList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setCardViewCategoriesListener()
        setCardViewViewAllListener()
    }

    fun setCardViewCategoriesListener(){

        val cardView : CardView = findViewById(R.id.cardViewCategories)
        cardView.setOnClickListener(View.OnClickListener {

            val intent: Intent = Intent(this, CategoryList::class.java)
            startActivity(intent)

        })
    }

    fun setCardViewViewAllListener(){
        val cardView : CardView = findViewById(R.id.cardViewViewAll)
        cardView.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, AccountListActivity::class.java)
            ActivityCompat.startActivity(this, intent, null)

        })

    }
}