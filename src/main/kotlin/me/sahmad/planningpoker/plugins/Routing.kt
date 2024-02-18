package me.sahmad.planningpoker.plugins

import io.ktor.server.application.Application
import me.sahmad.planningpoker.routes.createSessionRoutes

fun Application.configureRouting() {
    createSessionRoutes()
}
