package com.memento

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.Valid

@RestController
class TributeController(
    private val tributeRepository: TributeRepository
) {

    @GetMapping("/tribute")
    fun getTributeByPersonId(@RequestParam("personId") personId: Int): ResponseEntity<List<TributeEntity>> {
        val tributes = tributeRepository.findByPersonId(personId)

        return ResponseEntity(tributes, HttpStatus.OK)
    }

    @PostMapping("/tribute")
    fun saveTribute(@RequestBody @Valid tribute: TributeEntity): ResponseEntity<TributeEntity> {
        tributeRepository.save(tribute)

        return ResponseEntity(HttpStatus.CREATED)
    }
}

interface TributeRepository : JpaRepository<TributeEntity, Int> {
    fun findByPersonId(personId: Int): List<TributeEntity>
}

@Entity
data class TributeEntity(
    @Column
    val personId: Int,

    @Column
    val attachmentUrl: String,

    @Column
    val description: String,

    @Column
    val name: String,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null
)
