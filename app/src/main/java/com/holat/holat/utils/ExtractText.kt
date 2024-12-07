package com.holat.holat.utils

/**
Created by Mohamed Mohamed Taha on 12/7/2024
 */
fun extractText(input:String):String{
    val regex = Regex("""\["([^"]+)"\]""")
    return regex.find(input)?.groups?.get(1)?.value ?: ""
}