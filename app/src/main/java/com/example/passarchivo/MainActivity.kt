package com.example.passarchivo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import com.example.passarchivo.account.AccountListActivity
import com.example.passarchivo.category.CategoryList
import com.example.passarchivo.password.ChangePassword
import com.example.passarchivo.search.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setCardViewCategoriesListener()
        setCardViewViewAllListener()
        setCardViewChangePassListener()
        setCardViewSearchListener()
    }

    private fun setCardViewCategoriesListener() {

        val cardView: CardView = findViewById(R.id.cardViewCategories)
        cardView.setOnClickListener(View.OnClickListener {

            val intent: Intent = Intent(this, CategoryList::class.java)
            startActivity(intent)

        })
    }

    private fun setCardViewViewAllListener() {
        val cardView: CardView = findViewById(R.id.cardViewViewAll)
        cardView.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, AccountListActivity::class.java)
            ActivityCompat.startActivity(this, intent, null)

        })

    }

    private fun setCardViewChangePassListener() {
        val cardView: CardView = findViewById(R.id.cardViewChangePassword)
        cardView.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, ChangePassword::class.java)
            startActivity(intent)

        })
    }

    private fun setCardViewSearchListener() {
        val cardView: CardView = findViewById(R.id.cardViewSearch)
        cardView.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, SearchAccount::class.java)
            startActivity(intent)

        })


    }
}