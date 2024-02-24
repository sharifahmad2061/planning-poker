package me.sahmad.planningpoker.website

import io.ktor.server.application.Application
import me.sahmad.planningpoker.website.plugins.configureCallLogging
import me.sahmad.planningpoker.website.plugins.configureRouting
import me.sahmad.planningpoker.website.plugins.configureSessionAuthentication
import me.sahmad.planningpoker.website.plugins.configureWebSockets

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureSessionAuthentication()
    configureCallLogging()
    configureRouting()
    configureWebSockets()
}
