package com.memento.schedule

import org.springframework.data.jpa.repository.JpaRepository

interface ScheduleRepository : JpaRepository<ScheduleEntity, Int> {
    fun findByPersonId(personId: Int): List<ScheduleEntity>
    fun deleteByPersonId(personId: Int)
}
