package com.memento

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@RestController
class PersonController(
    private val personRepository: PersonRepository
) {
    @GetMapping("/person/{id}")
    fun getByPersonId(@PathVariable id: Int): ResponseEntity<PersonEntity> {
        val person = personRepository.findById(id)

        if (!person.isPresent) return ResponseEntity.notFound().build()
        return ResponseEntity(person.get(), HttpStatus.OK)
    }
}

interface PersonRepository : JpaRepository<PersonEntity, Int>

@Entity
data class PersonEntity(
    @Column(unique = true)
    val birthDate: String,

    @Column
    val deathDate: String,

    @Column
    val description: String,

    @Column
    val name: String,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int
)
