package com.carlostorres.apphabits.home.data.mappers

import com.carlostorres.apphabits.home.data.extensions.toStartOfDSateTimestamp
import com.carlostorres.apphabits.home.data.extensions.toTimeStamp
import com.carlostorres.apphabits.home.data.extensions.toZonedDateTime
import com.carlostorres.apphabits.home.data.local.entity.HabitEntity
import com.carlostorres.apphabits.home.data.remote.dto.HabitDto
import com.carlostorres.apphabits.home.data.remote.dto.HabitResponse
import com.carlostorres.apphabits.home.domain.model.Habit
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import kotlin.concurrent.thread

fun HabitEntity.toDomain() : Habit{

    return Habit (
        id = this.id,
        title = this.title,
        frequency = this.frequency.map {
            DayOfWeek.of(it)
        },
        completedDates = this.completedDates.map {
            it.toZonedDateTime().toLocalDate()
        },
        reminder = this.reminder.toZonedDateTime().toLocalTime(),
        startDate = this.startDate.toZonedDateTime()
    )

}

fun Habit.toEntity() : HabitEntity{

    return HabitEntity (
        id = this.id,
        title = this.title,
        frequency = this.frequency.map {
            it.value
        },
        completedDates = this.completedDates.map {
            it.toZonedDateTime().toTimeStamp()
        },
        reminder = this.reminder.toZonedDateTime().toTimeStamp(),
        startDate = this.startDate.toStartOfDSateTimestamp()
    )

}

fun HabitResponse.toDomain() : List<Habit>{

    return this.entries.map {
        val id = it.key
        val dto = it.value
        Habit(
            id = id,
            title = dto.title,
            frequency = dto.frequency.map {
                DayOfWeek.of(it)
            },
            completedDates = dto.completedDates?.map {
                it.toZonedDateTime().toLocalDate()
            } ?: emptyList(),
            reminder = dto.reminder.toZonedDateTime().toLocalTime(),
            startDate = dto.startDate.toZonedDateTime()
        )
    }

}

fun Habit.toDto() : HabitResponse{

    val dto = HabitDto (
        title = this.title,
        frequency = this.frequency.map { it.value },
        completedDates = this.completedDates.map {
            it.toZonedDateTime().toTimeStamp()
        },
        reminder = this.reminder.toZonedDateTime().toTimeStamp(),
        startDate = this.startDate.toStartOfDSateTimestamp()
    )

    return mapOf(id to dto)

}
