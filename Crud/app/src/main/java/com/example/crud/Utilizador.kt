package com.example.crud

class Utilizador(var username: String, var password: String) {
    override fun toString(): String {
        //return "Utilizador(user='$user', password='$password')"
        return username
    }
}