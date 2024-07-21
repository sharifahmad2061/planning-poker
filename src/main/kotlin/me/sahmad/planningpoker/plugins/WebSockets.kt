package me.sahmad.planningpoker.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.websocket.WebSockets
import io.ktor.server.websocket.pingPeriod
import java.time.Duration

fun Application.configureWebSockets() {
    install(WebSockets){
        pingPeriod = Duration.ofSeconds(15)
    }
}