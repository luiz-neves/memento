package com.memento

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import java.time.LocalDateTime.of

class ScheduleTests : FunSpec({
    
    val repository: ScheduleRepository = mockk()
    val target = ScheduleController(repository)
    
    test("Get schedule by person ordered by date") {
        val entities = listOf(
            ScheduleEntity(1, "name", of(2020, 3, 9, 10, 0), "description", "speaker", 1),
            ScheduleEntity(1, "name", of(2020, 1, 9, 10, 0), "description", "speaker", 2),
            ScheduleEntity(1, "name", of(2020, 2, 9, 10, 0), "description", "speaker", 3)
        )
        every { repository.findByPersonId(1) } returns entities
        
        assertSoftly(target.getByPersonId(1)) {
            body shouldBe entities.sortedBy { it.date }
            statusCode shouldBe HttpStatus.OK
        }
    }
    
    test("Saves what is passed through the API") {
        every { repository.save(any()) } answers { firstArg() }
        
        val response = target.save(ScheduleRequest(1, "event", "2020-02-09 10:00:00", "description", "speaker"))
        
        response.statusCode shouldBe CREATED
        verify(exactly = 1) { repository.save(ScheduleEntity(1, "event", of(2020, 2, 9, 10, 0, 0), "description", "speaker", null)) }
    }
    
    test("Deletes schedule") {
        every { repository.deleteByPersonId(any()) } just Runs
        val response = target.deleteByPersonId(1)
        
        response.statusCode shouldBe OK
        verify(exactly = 1) { repository.deleteByPersonId(1) }
        
    }
})
