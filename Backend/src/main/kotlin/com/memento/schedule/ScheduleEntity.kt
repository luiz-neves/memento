package com.memento.schedule

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "schedule")
data class ScheduleEntity(
    val personId: Int,

    @Column
    val nameEvent: String,

    @Column
    val date: LocalDateTime,

    @Column
    val description: String,

    @Column
    val speaker: String?,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?
)
