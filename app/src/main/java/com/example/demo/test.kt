package com.example.demo

abstract class Person(name: String, val age: Int){
    var name = name
        get() = "Name: $field"
        set(value) {
            field = value
        }

}

class Developer(name: String):Person(name, 22)

fun test(){
    val person = Developer("juan")
    val name = person.name
}



