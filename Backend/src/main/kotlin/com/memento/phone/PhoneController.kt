package com.memento.phone

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

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
