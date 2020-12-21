package com.memento.person

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "person")
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
