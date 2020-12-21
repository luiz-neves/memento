package com.memento

import com.memento.phone.PhoneController
import com.memento.phone.PhoneEntity
import com.memento.phone.PhoneRepository
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.http.HttpStatus

class PhoneTests : FunSpec({

    val repository: PhoneRepository = mockk()
    val target = PhoneController(repository)

    test("Returns what repository returns") {
        every { repository.findAll() } returns listOf(dummyPhone)

        assertSoftly(target.findAll()) {
            body shouldBe listOf(dummyPhone)
            statusCode shouldBe HttpStatus.OK
        }
    }

    test("Saves what is passed through the API") {
        every { repository.save(any()) } answers { firstArg() }

        target.save(dummyPhone).statusCode shouldBe HttpStatus.CREATED
        verify(exactly = 1) { repository.save(any()) }
    }
})

private val dummyPhone = PhoneEntity("1", "2", 3)
