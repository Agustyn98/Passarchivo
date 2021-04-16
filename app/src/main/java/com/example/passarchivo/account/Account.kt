package com.example.passarchivo.account

class Account(
    id: Int = -1,
    name: String,
    email: String = "",
    username: String = "",
    password: String,
    note: String = "",
    idCategory: Int,
    customFieldName: String = "",
    customFieldValue: String = ""
) {

    private var id = id
    private var name = name
    private var email = email;
    private var username = username
    private var password = password
    private var note = note
    private var customFieldName = customFieldName
    private var customFieldValue = customFieldValue
    private var idCategory = idCategory

    fun getId(): Int {
        return id;
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

    fun getCustonFieldName() : String{
        return customFieldName
    }

    fun getCustonFieldValue() : String{
        return customFieldValue
    }

    fun getIdCategory(): Int {
        return idCategory
    }

}