package com.example.passarchivo

class Category(id: Int = -1 , name: String, imageId: Int) {

    private var id : Int = id
    private var name : String = name
    private var imageId : Int = imageId




    fun getId(): Int {
        return id
    }

    fun getName(): String{
        return name
    }

    fun getImageId() : Int{
        return imageId
    }



}