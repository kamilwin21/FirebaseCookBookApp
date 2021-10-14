package com.example.appforlogindata.MainActivity.Classes
//Klasa do formowania przepisów
data class Recipes(
    var id:String,
    var uid:String,
    var nazwa: String,
    var kategoria: String,
    var opis: String
) {
    constructor(): this("","","","", "")
}