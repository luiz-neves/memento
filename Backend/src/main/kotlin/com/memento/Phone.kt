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

    @PostMapping("/phone")
    fun save(
        @RequestBody phone: PhoneEntity
    ): ResponseEntity<PhoneEntity> {
        phoneRepository.save(phone)

        return ResponseEntity(HttpStatus.CREATED)
    }

    @GetMapping("/phone")
    fun findAll(): ResponseEntity<List<PhoneEntity>> {
        val phone = phoneRepository.findAll()

        return ResponseEntity(phone, HttpStatus.OK)
    }
}

interface PhoneRepository : JpaRepository<PhoneEntity, Int>

@Entity(name = "phone")
data class PhoneEntity(
    @Column(unique = true)
    val phone: String,

    @Column
    val name: String,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int
)
