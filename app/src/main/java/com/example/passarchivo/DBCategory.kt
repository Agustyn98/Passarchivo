package com.example.passarchivo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.String

class DBCategory(context: Context?) : SQLiteOpenHelper(context, "passarchivo", null, 1) {

    private val tableName = "Category"
    private val COLUMN_ID = "id"
    private val COLUMN_NAME = "name"
    private val COLUMN_IMAGE_ID = "imageId"

    override fun onCreate(db: SQLiteDatabase?) {

        val createTableStatement = "CREATE TABLE '$tableName' ('$COLUMN_ID' INTEGER NOT NULL,'$COLUMN_NAME' TEXT NOT NULL,'$COLUMN_IMAGE_ID' INTEGER, PRIMARY KEY('$COLUMN_ID') );"

        if (db != null) {
            db.execSQL(createTableStatement)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //?
    }

    fun addOne(category: Category): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME, category.getName())
        contentValues.put(COLUMN_IMAGE_ID, category.getImageId())

        val result = db.insert(tableName, null, contentValues)
        db.close()
        return result

    }

    fun getAll() : ArrayList<Category>{
        var categoriesArray = ArrayList<Category>()
        val db = this.readableDatabase
        val queryString = "SELECT * FROM " + tableName;

        //Cursor = like a complex array of items that stores the query results
        var cursor : Cursor = db.rawQuery(queryString, null)
        //db.query()
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val imageId = cursor.getInt(2)
                val category : Category = Category(id, name, imageId)

                categoriesArray.add(category)

            } while (cursor.moveToNext())
        }

        return categoriesArray;
    }

    fun deleteOne(id: Int?) : Int {
        val db = this.writableDatabase
        val result = db.delete(
            tableName,
            COLUMN_ID.toString() + " = ?",
            arrayOf(String.valueOf(id))
        );
        db.close()
        return result;
    }

    fun updateOne(category: Category) : Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_ID, category.getId())
        contentValues.put(COLUMN_NAME, category.getName()) // EmpModelClass Name
        contentValues.put(COLUMN_IMAGE_ID, category.getImageId() ) // EmpModelClass Email

        // Updating Row
        val success = db.update(tableName, contentValues,"id= " + category.getId() ,null)
        db.close()
        return success
    }

}