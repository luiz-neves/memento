package com.memento

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    embeddedServer(Netty) {
        routes()
    }.start(wait = true)
}

fun Application.routes() {
    routing {
        get("/") {
            call.respondText("Hello, World!")
        }
    }
}
