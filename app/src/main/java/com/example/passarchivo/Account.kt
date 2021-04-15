package com.example.passarchivo

class Account(id: Int = -1, name: String, email : String="", username: String="", password : String, note: String="", idCategory: Int) {

    private var id = id
    private var name = name
    private var email = email;
    private var username = username
    private var password = password
    private var note = note
    private var idCategory = idCategory

    fun getId() : Int{
        return id;
    }

    fun getName() : String{
        return name
    }

    fun getEmail() : String {
        return email
    }

    fun getUserName() : String{
        return username
    }

    fun getPassword():String{
        return password
    }

    fun getNote(): String{
        return note
    }

    fun getIdCategory():Int{
        return idCategory
    }

}