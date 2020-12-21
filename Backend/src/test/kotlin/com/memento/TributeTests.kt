package com.memento

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.http.HttpStatus

class TributeTests : FunSpec({
    
    val repository: TributeRepository = mockk()
    val target = TributeController(repository)
    
    test("Returns what repository returns") {
        every { repository.findByPersonId(1) } returns listOf(dummyTribute)
        
        assertSoftly(target.getByPersonId(1)) {
            body shouldBe listOf(dummyTribute)
            statusCode shouldBe HttpStatus.OK
        }
    }
    
    test("Saves what is passed through the API") {
        every { repository.save(any()) } answers { firstArg() }
        
        target.save(dummyTribute).statusCode shouldBe HttpStatus.CREATED
        verify(exactly = 1) { repository.save(any()) }
    }
})

private val dummyTribute = TributeEntity(1, "a", "b", "c", 2)
