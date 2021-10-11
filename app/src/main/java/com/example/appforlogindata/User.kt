package com.example.appforlogindata

data class User (
    var id:String,
    var email:String,
    var password:String,
    var name: String,
    var surname: String,
    var age:String


        ){
    constructor(): this("","","","","",""){

    }
}