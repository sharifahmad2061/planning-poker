package me.sahmad.planningpoker

import io.ktor.server.application.Application
import me.sahmad.planningpoker.plugins.configureRouting
import me.sahmad.planningpoker.plugins.configureSerialization

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureRouting()
    configureSerialization()
}