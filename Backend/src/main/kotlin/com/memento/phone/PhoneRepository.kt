package com.memento.phone

import org.springframework.data.jpa.repository.JpaRepository

interface PhoneRepository : JpaRepository<PhoneEntity, Int>
