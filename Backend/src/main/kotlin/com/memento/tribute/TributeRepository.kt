package com.memento.tribute

import org.springframework.data.jpa.repository.JpaRepository

interface TributeRepository : JpaRepository<TributeEntity, Int> {
    fun findByPersonId(personId: Int): List<TributeEntity>
}
