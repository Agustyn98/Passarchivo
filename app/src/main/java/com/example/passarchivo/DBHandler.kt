package com.example.passarchivo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.String

class DBHandler(context: Context?) : SQLiteOpenHelper(context, "passarchivo", null, 1) {

    private val tableNameCategory = "Category"
    private val COLUMN_ID = "id"
    private val COLUMN_NAME = "name"
    private val COLUMN_IMAGE_ID = "imageId"

    private val tableNameAccount = "Account"
    private val COLUMN_IDACCOUNT = "id"
    private val COLUMN_NAMEACCOUNT = "name"
    private val COLUMN_EMAIL = "email"
    private val COLUMN_USERNAME = "username"
    private val COLUMN_PASSWORD = "password"
    private val COLUMN_NOTE = "note"
    private val COLUMN_ID_CATEGORY = "idCategory"

    override fun onCreate(db: SQLiteDatabase?) {

        val createTableStatementCategory =
            "CREATE TABLE '$tableNameCategory' ('$COLUMN_ID' INTEGER NOT NULL,'$COLUMN_NAME' TEXT NOT NULL,'$COLUMN_IMAGE_ID' INTEGER, PRIMARY KEY('$COLUMN_ID') );"
        val createTableStatementAccount =
            "CREATE TABLE $tableNameAccount ($COLUMN_IDACCOUNT INTEGER NOT NULL,$COLUMN_NAMEACCOUNT TEXT,$COLUMN_EMAIL TEXT,$COLUMN_USERNAME TEXT, $COLUMN_PASSWORD TEXT, $COLUMN_NOTE TEXT, $COLUMN_ID_CATEGORY INTEGER, PRIMARY KEY ($COLUMN_ID ));"

        if (db != null) {
            db.execSQL(createTableStatementAccount)
            db.execSQL(createTableStatementCategory)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //?
    }

    fun addOneCategory(category: Category): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME, category.getName())
        contentValues.put(COLUMN_IMAGE_ID, category.getImageId())

        val result = db.insert(tableNameCategory, null, contentValues)
        db.close()
        return result
    }

    fun addOneAccount(account: Account): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(COLUMN_NAMEACCOUNT, account.getName())
        contentValues.put(COLUMN_EMAIL, account.getEmail())
        contentValues.put(COLUMN_USERNAME, account.getUserName())
        contentValues.put(COLUMN_PASSWORD, account.getPassword())
        contentValues.put(COLUMN_NOTE, account.getNote())
        contentValues.put(COLUMN_ID_CATEGORY, account.getIdCategory())

        val result = db.insert(tableNameAccount, null, contentValues)
        db.close()
        return result
    }

    fun getAllCategories(): ArrayList<Category> {
        var categoriesArray = ArrayList<Category>()
        val db = this.readableDatabase
        val queryString = "SELECT * FROM " + tableNameCategory;

        //Cursor = like a complex array of items that stores the query results
        var cursor: Cursor = db.rawQuery(queryString, null)
        //db.query()
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val imageId = cursor.getInt(2)
                val category: Category = Category(id, name, imageId)

                categoriesArray.add(category)

            } while (cursor.moveToNext())
        }

        return categoriesArray;
    }


    fun getAllAccountsById(idCategory: Int): ArrayList<Account> {
        var accountArray = ArrayList<Account>()
        val db = this.readableDatabase
        val queryString =
            "SELECT * FROM " + tableNameAccount + " WHERE $COLUMN_ID_CATEGORY = $idCategory ";

        //Cursor = like a complex array of items that stores the query results
        var cursor: Cursor = db.rawQuery(queryString, null)
        //db.query()
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val email = cursor.getString(2)
                val username = cursor.getString(3)
                val password = cursor.getString(4)
                val note = cursor.getString(5)
                val idCategory = cursor.getInt(6)
                val account: Account =
                    Account(id, name, email, username, password, note, idCategory)

                accountArray.add(account)

            } while (cursor.moveToNext())
        }

        return accountArray;
    }

    fun getAllAccounts(): ArrayList<Account> {
        var accountArray = ArrayList<Account>()
        val db = this.readableDatabase
        val queryString = "SELECT * FROM " + tableNameAccount;

        //Cursor = like a complex array of items that stores the query results
        var cursor: Cursor = db.rawQuery(queryString, null)
        //db.query()
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val email = cursor.getString(2)
                val username = cursor.getString(3)
                val password = cursor.getString(4)
                val note = cursor.getString(5)
                val idCategory = cursor.getInt(6)
                val account: Account =
                    Account(id, name, email, username, password, note, idCategory)

                accountArray.add(account)

            } while (cursor.moveToNext())
        }

        return accountArray;
    }

    fun getOneAccount(id: Int) : Account?{
        val db = this.readableDatabase
        val queryString = "SELECT * FROM " + tableNameAccount + " WHERE id = " + id;
        var account: Account?

        var cursor: Cursor = db.rawQuery(queryString, null)

        if (cursor.moveToFirst()) {

            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val email = cursor.getString(2)
            val username = cursor.getString(3)
            val password = cursor.getString(4)
            val note = cursor.getString(5)
            val idCategory = cursor.getInt(6)
            account = Account(id, name, email, username, password, note, idCategory)
            return account;

        }
        return null

    }

    fun deleteOneCategory(id: Int?): Int {
        val db = this.writableDatabase
        val result = db.delete(
            tableNameCategory,
            COLUMN_ID.toString() + " = ?",
            arrayOf(String.valueOf(id))
        );
        db.close()
        return result;
    }

    //pending:
    fun deleteOneAccount(id: Int?): Int {
        val db = this.writableDatabase
        val result = db.delete(
            tableNameAccount,
            COLUMN_ID.toString() + " = ?",
            arrayOf(String.valueOf(id))
        );
        db.close()
        return result;
    }

    fun deleteAccountsByCategory(id_category: Int?): Int {
        val db = this.writableDatabase
        val result = db.delete(
            tableNameAccount,
            COLUMN_ID_CATEGORY.toString() + " = ?",
            arrayOf(String.valueOf(id_category))
        );
        db.close()
        return result;
    }

    fun updateOneCategory(category: Category): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        //contentValues.put(COLUMN_ID, category.getId())
        contentValues.put(COLUMN_NAME, category.getName())
        contentValues.put(COLUMN_IMAGE_ID, category.getImageId())

        // Updating Row
        val success = db.update(tableNameCategory, contentValues, "id= " + category.getId(), null)
        db.close()
        return success
    }

    fun updateOneAccount(account: Account): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        //contentValues.put(COLUMN_ID, category.getId())
        contentValues.put(COLUMN_NAMEACCOUNT, account.getName())
        contentValues.put(COLUMN_EMAIL, account.getEmail())
        contentValues.put(COLUMN_USERNAME, account.getUserName())
        contentValues.put(COLUMN_PASSWORD, account.getPassword())
        contentValues.put(COLUMN_NOTE, account.getNote())

        // Updating Row
        val success = db.update(tableNameAccount, contentValues, "id= " + account.getId(), null)
        db.close()
        return success
    }

}