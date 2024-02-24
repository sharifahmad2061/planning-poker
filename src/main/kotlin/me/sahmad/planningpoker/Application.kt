package me.sahmad.planningpoker

import io.ktor.server.application.Application
import me.sahmad.planningpoker.plugins.configureCallLogging
import me.sahmad.planningpoker.plugins.configureRouting
import me.sahmad.planningpoker.plugins.configureSerialization
import me.sahmad.planningpoker.plugins.configureSessionAuthentication

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureSerialization()
    configureCallLogging()
    configureSessionAuthentication()
    configureRouting()
}
