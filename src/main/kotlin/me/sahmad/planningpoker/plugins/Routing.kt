package me.sahmad.planningpoker.plugins

import io.ktor.server.application.Application
import me.sahmad.planningpoker.routes.createSessionRoutes
import me.sahmad.planningpoker.routes.createUserRoutes

fun Application.configureRouting() {
    createSessionRoutes()
    createUserRoutes()
}
