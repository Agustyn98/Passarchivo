package com.example.passarchivo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.passarchivo.account.Account
import com.example.passarchivo.category.Category
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
    private val COLUMN_CUSTOM_NAME = "customFieldName"
    private val COLUMN_CUSTOM_VALUE = "customFieldVal"

    private val tableNameMainPassword = "MainPassword"
    private val COLUMN_MAIN_PASS = "password"

    override fun onCreate(db: SQLiteDatabase?) {

        val createTableStatementCategory =
            "CREATE TABLE '$tableNameCategory' ('$COLUMN_ID' INTEGER NOT NULL,'$COLUMN_NAME' TEXT NOT NULL,'$COLUMN_IMAGE_ID' INTEGER, PRIMARY KEY('$COLUMN_ID') );"
        val createTableStatementAccount =
            "CREATE TABLE $tableNameAccount ($COLUMN_IDACCOUNT INTEGER NOT NULL,$COLUMN_NAMEACCOUNT TEXT,$COLUMN_EMAIL TEXT,$COLUMN_USERNAME TEXT, $COLUMN_PASSWORD TEXT, $COLUMN_NOTE TEXT,$COLUMN_CUSTOM_NAME TEXT, $COLUMN_CUSTOM_VALUE TEXT ,$COLUMN_ID_CATEGORY INTEGER, PRIMARY KEY ($COLUMN_ID ));"
        val createTableStatementMainPassword =
            "CREATE TABLE $tableNameMainPassword ($COLUMN_MAIN_PASS INTEGER NOT NULL)"

        if (db != null) {
            db.execSQL(createTableStatementAccount)
            db.execSQL(createTableStatementCategory)
            db.execSQL(createTableStatementMainPassword)
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
        contentValues.put(COLUMN_CUSTOM_NAME, account.getCustomFieldName())
        contentValues.put(COLUMN_CUSTOM_VALUE, account.getCustomFieldValue())

        val result = db.insert(tableNameAccount, null, contentValues)
        db.close()
        return result
    }

    fun addMainPass(password: kotlin.String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_MAIN_PASS, password)
        val result = db.insert(tableNameMainPassword, null, contentValues)
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
        val cursor: Cursor = db.rawQuery(queryString, null)
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
                val customFieldName = cursor.getString(7)
                val customFieldValue = cursor.getString(8)
                val account: Account =
                    Account(
                        id,
                        name,
                        email,
                        username,
                        password,
                        note,
                        idCategory,
                        customFieldName,
                        customFieldValue
                    )

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
                val customFieldName = cursor.getString(7)
                val customFieldValue = cursor.getString(8)
                val account: Account =
                    Account(
                        id,
                        name,
                        email,
                        username,
                        password,
                        note,
                        idCategory,
                        customFieldName,
                        customFieldValue
                    )

                accountArray.add(account)

            } while (cursor.moveToNext())
        }

        return accountArray;
    }

    fun getOneAccount(id: Int): Account? {
        val db = this.readableDatabase
        val queryString = "SELECT * FROM " + tableNameAccount + " WHERE id = " + id;
        val account: Account?

        val cursor: Cursor = db.rawQuery(queryString, null)

        if (cursor.moveToFirst()) {

            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val email = cursor.getString(2)
            val username = cursor.getString(3)
            val password = cursor.getString(4)
            val note = cursor.getString(5)
            val customFieldName = cursor.getString(6)
            val customFieldValue = cursor.getString(7)
            val idCategory = cursor.getInt(8)
            account = Account(
                id,
                name,
                email,
                username,
                password,
                note,
                idCategory,
                customFieldName,
                customFieldValue
            )

            return account;

        }
        return null

    }

    fun getMainPass(): kotlin.String {
        val db = this.readableDatabase
        val queryString = "SELECT * FROM " + tableNameMainPassword
        val cursor: Cursor = db.rawQuery(queryString, null)
        if (cursor.moveToFirst()) {
            return cursor.getString(0)
        }
        return ""

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
        contentValues.put(COLUMN_CUSTOM_NAME, account.getCustomFieldName())
        contentValues.put(COLUMN_CUSTOM_VALUE, account.getCustomFieldValue())
        contentValues.put(COLUMN_ID_CATEGORY, account.getIdCategory())

        // Updating Row
        val success = db.update(tableNameAccount, contentValues, "id= " + account.getId(), null)
        db.close()
        return success
    }

    fun updateMainPass(newPass: kotlin.String): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_MAIN_PASS, newPass)

        val success = db.update(tableNameMainPassword, contentValues, null, null)
        db.close()
        return success

    }

    fun searchAccounts(search : kotlin.String): ArrayList<Account> {
        var accountArray = ArrayList<Account>()
        val db = this.readableDatabase
        val queryString = "SELECT * FROM $tableNameAccount WHERE $COLUMN_NAMEACCOUNT LIKE '%$search%' OR $COLUMN_EMAIL LIKE '%$search%' OR $COLUMN_USERNAME LIKE '%$search%' OR $COLUMN_NOTE LIKE '%$search%' OR $COLUMN_CUSTOM_NAME LIKE '%$search%';"

        //Cursor = like a complex array of items that stores the query results
        val cursor: Cursor = db.rawQuery(queryString, null)
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
                val customFieldName = cursor.getString(7)
                val customFieldValue = cursor.getString(8)
                val account: Account =
                    Account(
                        id,
                        name,
                        email,
                        username,
                        password,
                        note,
                        idCategory,
                        customFieldName,
                        customFieldValue
                    )

                accountArray.add(account)

            } while (cursor.moveToNext())
        }

        return accountArray;
    }
}