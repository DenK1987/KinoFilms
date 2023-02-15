package com.kinofilms.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kinofilms.models.Person

class TypeConverter {
    @TypeConverter
    fun fromStringToList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListToString(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToListPerson(value: String): List<Person> {
        return ArrayList()
    }

    @TypeConverter
    fun fromListPersonToString(list: List<Person>): String {
        return ""
    }
}