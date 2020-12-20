package com.memento

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@RestController
class PhoneController(
    private val phoneRepository: PhoneRepository
) {

    @PostMapping("/insertPhone")
    fun postInsertPhone(
        @RequestBody phoneEntity: PhoneEntity
    ): ResponseEntity<PhoneEntity> {
        phoneRepository.save(phoneEntity)

        return ResponseEntity(HttpStatus.CREATED)
    }

    @GetMapping("/findAllPhones")
    fun findAll(): ResponseEntity<List<PhoneEntity>> {
        val person = phoneRepository.findAll()

        return ResponseEntity(person, HttpStatus.OK)
    }
}

interface PhoneRepository : JpaRepository<PhoneEntity, Int>

@Entity
data class PhoneEntity(
    @Column(unique = true)
    val phone: String,

    @Column
    val name: String,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int
)
