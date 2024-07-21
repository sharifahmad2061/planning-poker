package me.sahmad.planningpoker

import io.ktor.server.application.Application
import me.sahmad.planningpoker.plugins.configureCallLogging
import me.sahmad.planningpoker.plugins.configureRouting
import me.sahmad.planningpoker.plugins.configureSerialization
import me.sahmad.planningpoker.plugins.configureSessionAuthentication
import me.sahmad.planningpoker.plugins.configureTemplating
import me.sahmad.planningpoker.plugins.configureWebSockets

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureSessionAuthentication()
    configureSerialization()
    configureCallLogging()
    configureWebSockets()
    configureTemplating()
    configureRouting()
}
