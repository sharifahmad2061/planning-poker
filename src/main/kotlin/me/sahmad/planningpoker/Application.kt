package me.sahmad.planningpoker

import io.ktor.serialization.kotlinx.json.jsonimport io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation

fun Application.main() {
    embeddedServer(Netty, 8080) {
        install(ContentNegotiation) {
            json()
        }
    }
}