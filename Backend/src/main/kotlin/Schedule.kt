package com.memento

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.*
import javax.validation.Valid

@RestController
class Schedule(
        private val scheduleRepository: ScheduleRepository
) {

    @GetMapping("/schedule")
    fun getScheduleByPersonId(@RequestParam("personId") personId: Int): ResponseEntity<List<ScheduleEntity>> {
        val schedules = scheduleRepository.findByPersonId(personId)

        val sortedSchedulesByDate = schedules.sortedBy { entity -> entity.date }
        return ResponseEntity(sortedSchedulesByDate, HttpStatus.OK)
    }

    @PostMapping("/schedule")
    fun saveSchedule(@RequestBody @Valid request: ScheduleRequest): ResponseEntity<TributeEntity> {
        val date = request.date

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val localDateTime = LocalDateTime.parse(date, formatter)

        val schedule = ScheduleEntity(request.personId, request.nameEvent, localDateTime, request.description, request.speaker, null)
        scheduleRepository.save(schedule)

        return ResponseEntity(HttpStatus.CREATED)
    }

    @DeleteMapping("/schedule")
    fun deleteSchedule(@RequestParam("personId") personId: Int): ResponseEntity<TributeEntity> {
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

@Entity
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
