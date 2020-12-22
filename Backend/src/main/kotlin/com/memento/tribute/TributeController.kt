package com.memento.tribute

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class TributeController(
    private val tributeRepository: TributeRepository
) {

    @GetMapping("/tribute")
    fun getByPersonId(@RequestParam("personId") personId: Int): ResponseEntity<List<TributeEntity>> {
        val tributes = tributeRepository.findByPersonId(personId)

        return ResponseEntity(tributes, HttpStatus.OK)
    }

    @PostMapping("/tribute")
    fun save(@RequestBody @Valid tribute: TributeEntity): ResponseEntity<TributeEntity> {
        tributeRepository.save(tribute)

        return ResponseEntity(HttpStatus.CREATED)
    }
}
