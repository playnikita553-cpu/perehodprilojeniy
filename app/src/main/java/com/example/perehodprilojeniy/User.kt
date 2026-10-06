package com.example.perehodprilojeniy

import java.io.Serializable

data class User(
    var lastName: String,
    var firstName: String,
    var age: Int,
    var phone: String,
    var adress: String,
    var site: String
) : Serializable {

    fun smallFio(): String = lastName + " " + firstName[0] + "."
}