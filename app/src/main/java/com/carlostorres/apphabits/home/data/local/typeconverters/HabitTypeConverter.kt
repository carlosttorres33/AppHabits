package com.carlostorres.apphabits.home.data.local.typeconverters

import android.util.Log
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.util.joinIntoString
import androidx.room.util.splitToIntList
import com.squareup.moshi.Moshi

@ProvidedTypeConverter
class HabitTypeConverter(
    private val moshi : Moshi
) {

    @TypeConverter
    fun fromFrequency(days : List<Int>) : String {

        return joinIntoString(days) ?: ""

    }

    @TypeConverter
    fun toFrequency(value : String): List<Int>{

        return splitToIntList(value) ?: emptyList()

    }

    @TypeConverter
    fun fromCompletedDates(days : List<Long>) : String {

        return joinIntoString(days) ?: ""

    }

    @TypeConverter
    fun toCompletedDates(value : String): List<Long>{

        return splitToLongList(value) ?: emptyList()

    }

    private fun splitToLongList(input: String?): List<Long>?{
        return input?.split(',')?.mapNotNull {
            try {
                it.toLong()
            }catch (ex: NumberFormatException){
                Log.e("Room", "Malformed Long Linst", ex)
                null
            }
        }
    }

    private fun joinIntoString(input: List<Long>?): String?{
        return input?.joinToString(",")
    }

}