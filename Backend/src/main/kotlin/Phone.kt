package com.memento

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Id
import javax.persistence.Entity
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.validation.constraints.NotNull

@RestController
class Phone {

    @Autowired
    lateinit var phoneRepository: PhoneRepository

    @PostMapping("/insertPhone")
    fun postInsertPhone(@RequestBody phoneEntity: PhoneEntity): ResponseEntity<PhoneEntity> {

        phoneRepository.save(phoneEntity)

        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/findAllPhones")
    fun findAll(): ResponseEntity<MutableList<PhoneEntity>> {
        val person = phoneRepository.findAll()
        return ResponseEntity(person, HttpStatus.OK)
    }
}

interface PhoneRepository : JpaRepository<PhoneEntity, Int>

@Entity
data class PhoneEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null,

    @Column(unique = true)
    @NotNull
    var phone: String? = null,

    @Column
    @NotNull
    var name: String? = null
)
