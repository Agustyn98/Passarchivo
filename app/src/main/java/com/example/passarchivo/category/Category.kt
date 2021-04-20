package com.example.passarchivo.category

class Category(
    private var id: Int = -1, private var name: String, private var imageId: Int
) {


    fun getId(): Int {
        return id
    }

    fun getName(): String {
        return name
    }

    fun getImageId(): Int {
        return imageId
    }

    override fun toString(): String {
        return name
    }


}