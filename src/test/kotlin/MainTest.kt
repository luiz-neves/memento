package com.memento

import io.kotest.assertions.ktor.shouldHaveContent
import io.kotest.core.spec.style.FunSpec
import io.ktor.http.HttpMethod.Companion.Get
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication

class MainTest : FunSpec({

    test("Responds hello world") {
        withTestApplication({ routes() }) {
            val call = handleRequest(Get, "/")
            call.response shouldHaveContent "Hello, World!"
        }
    }

})