package me.sahmad.planningpoker.website.plugins

import io.ktor.server.application.Application
import io.ktor.server.routing.get
import me.sahmad.planningpoker.website.routes.createHomePageRoutes
import me.sahmad.planningpoker.website.routes.createSessionRoutes

fun Application.configureRouting() {
    createHomePageRoutes()
    createSessionRoutes()
}
