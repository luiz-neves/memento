package com.memento

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.http.HttpStatus
import java.util.Optional

class PersonTests : FunSpec({

    val repository: PersonRepository = mockk()
    val target = PersonController(repository)

    test("Returns what repository returns") {
        every { repository.findById(1) } returns Optional.of(dummyPerson)

        assertSoftly(target.getById(1)) {
            body shouldBe dummyPerson
            statusCode shouldBe HttpStatus.OK
        }
    }

    test("Return not found for a not found person") {
        every { repository.findById(1) } returns Optional.empty()

        assertSoftly(target.getById(1)) {
            statusCode shouldBe HttpStatus.NOT_FOUND
        }
    }
})

private val dummyPerson = PersonEntity("1", "2", "3", "4", 5)