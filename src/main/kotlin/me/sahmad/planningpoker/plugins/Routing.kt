package me.sahmad.planningpoker.plugins

import io.ktor.server.application.Application
import me.sahmad.planningpoker.routes.sessionRoutes

fun Application.configureRouting() {
    sessionRoutes()
}