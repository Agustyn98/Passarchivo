package com.example.passarchivo.account

class Account(
    private var id: Int = -1, //Same as this.id = id
    private var name: String,
    private var email: String = "",
    private var username: String = "",
    private var password: String,
    private var note: String = "",
    private var idCategory: Int,
    private var customFieldName: String = "",
    private var customFieldValue: String = ""
) {

    fun getId(): Int {
        return id
    }

    fun getName(): String {
        return name
    }

    fun getEmail(): String {
        return email
    }

    fun getUserName(): String {
        return username
    }

    fun getPassword(): String {
        return password
    }

    fun getNote(): String {
        return note
    }

    fun getCustomFieldName() : String{
        return customFieldName
    }

    fun getCustomFieldValue() : String{
        return customFieldValue
    }

    fun getIdCategory(): Int {
        return idCategory
    }

    override fun toString(): String {
        return name
    }

}