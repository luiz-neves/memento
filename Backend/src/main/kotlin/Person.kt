package com.memento

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import javax.persistence.Id
import javax.persistence.Entity
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.validation.constraints.NotNull

@RestController
class PersonController {
    @Autowired
    lateinit var personRepository: PersonRepository

    @GetMapping("/person/{id}")
    fun getByPersonId(@PathVariable id: Int): ResponseEntity<PersonEntity> {
        val person = personRepository.findById(id)

        if (!person.isPresent) {
            return ResponseEntity.notFound().build()
        }

        return ResponseEntity(person.get(), HttpStatus.OK)
    }
}

interface PersonRepository : JpaRepository<PersonEntity, Int>

@Entity
data class PersonEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null,

    @Column(unique = true)
    @NotNull
    var birthDate: String? = null,

    @Column
    @NotNull
    var deathDate: String? = null,

    @Column
    @NotNull
    var description: String? = null,

    @Column
    @NotNull
    var name: String? = null
)
