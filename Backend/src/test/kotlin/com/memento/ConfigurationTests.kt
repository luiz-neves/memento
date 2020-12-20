package com.memento

import io.kotest.core.spec.style.ShouldSpec
import io.mockk.mockk
import io.mockk.verify
import org.springframework.web.servlet.config.annotation.CorsRegistry

class ConfigurationTests : ShouldSpec({
    
    val target = Configuration()
    
    should("Add cors configuration") {
        val registry = mockk<CorsRegistry>(relaxed = true)
        
        target.corsConfigurer().addCorsMappings(registry)
        
        verify(exactly = 1) { registry.addMapping("/**") }
    }
}) 