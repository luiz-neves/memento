package com.memento

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.Valid

@RestController
class ScheduleController(
    private val scheduleRepository: ScheduleRepository
) {

    @GetMapping("/schedule")
    fun getByPersonId(@RequestParam("personId") personId: Int): ResponseEntity<List<ScheduleEntity>> {
        val schedules = scheduleRepository.findByPersonId(personId)

        val schedulesSortedByDate = schedules.sortedBy { it.date }
        return ResponseEntity(schedulesSortedByDate, HttpStatus.OK)
    }

    @PostMapping("/schedule")
    fun save(@RequestBody @Valid request: ScheduleRequest): ResponseEntity<TributeEntity> {
        val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateFormatted = LocalDateTime.parse(request.date, dateTimeFormatter)

        val schedule = ScheduleEntity(
            request.personId,
            request.nameEvent,
            dateFormatted,
            request.description,
            request.speaker,
            null
        )
        scheduleRepository.save(schedule)

        return ResponseEntity(HttpStatus.CREATED)
    }

    @DeleteMapping("/schedule")
    fun deleteByPersonId(@RequestParam("personId") personId: Int): ResponseEntity<TributeEntity> {
        scheduleRepository.deleteByPersonId(personId)

        return ResponseEntity(HttpStatus.OK)
    }
}

interface ScheduleRepository : JpaRepository<ScheduleEntity, Int> {
    fun findByPersonId(personId: Int): List<ScheduleEntity>
    fun deleteByPersonId(personId: Int)
}

data class ScheduleRequest(
    val personId: Int,

    val nameEvent: String,

    val date: String,

    val description: String,

    val speaker: String?,
)

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
