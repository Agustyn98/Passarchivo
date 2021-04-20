package com.example.passarchivo.category

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.passarchivo.DBHandler
import com.example.passarchivo.R


class CategoryList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)

        buttonAddListener()
        setRecycleViewAdapter()
    }


    fun setRecycleViewAdapter() {

        val recyclerViewCategories: RecyclerView = findViewById(R.id.listViewCategories)
        recyclerViewCategories.layoutManager = LinearLayoutManager(this)
        //create array from DB
        val array1 = getAllCategories()
        recyclerViewCategories.adapter = AdapterCategory(array1)

        val textView : TextView = findViewById(R.id.textViewNoItemsCategory)
        println("size array: ${array1.size}")
        if(array1.size <= 0)
            textView.visibility = View.VISIBLE
        else
            textView.visibility = View.GONE

    }

    private fun getAllCategories(): ArrayList<Category> {
        val db: DBHandler = DBHandler(this@CategoryList)

        return db.getAllCategories()
    }


    private var ACTIVITY_ADD_REQUEST_CODE = 1
    private var ACTIVITY_EDIT_REQUEST_CODE = 2
    private fun buttonAddListener() {
        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        buttonAdd.setOnClickListener(View.OnClickListener {
            //Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, EditCategory::class.java)
            startActivityForResult(intent, ACTIVITY_ADD_REQUEST_CODE)

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (ACTIVITY_ADD_REQUEST_CODE == requestCode) {
            if (resultCode == Activity.RESULT_OK) {
                //val returnId = data!!.getIntExtra("id", -1)
                val returnName = data!!.getStringExtra("categoryName")
                val returnImageId = data.getIntExtra("categoryImageId", -1)
                val db: DBHandler = DBHandler(this@CategoryList)
                val category: Category =
                    Category(name = returnName.toString(), imageId = returnImageId)
                db.addOneCategory(category)
                setRecycleViewAdapter()

            }
        } else if (ACTIVITY_EDIT_REQUEST_CODE == requestCode) {
            if (resultCode == Activity.RESULT_OK) {

                val returnName = data!!.getStringExtra("categoryName")
                val returnId = data.getIntExtra("id", -1)
                val returnImageId = data.getIntExtra("categoryImageId", -1)

                var db: DBHandler = DBHandler(this)
                val category: Category = Category(returnId, returnName.toString(), returnImageId)
                db.updateOneCategory(category)
                setRecycleViewAdapter()

            }
        }
    }


}