package com.memento.person

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PersonController(
    private val personRepository: PersonRepository
) {
    @GetMapping("/person/{id}")
    fun getById(@PathVariable id: Int): ResponseEntity<PersonEntity> {
        val person = personRepository.findById(id)

        if (!person.isPresent) return ResponseEntity.notFound().build()
        return ResponseEntity(person.get(), HttpStatus.OK)
    }
}
