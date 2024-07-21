package me.sahmad.planningpoker.plugins

import io.ktor.server.application.Application
import me.sahmad.planningpoker.routes.createHomePageRoutes
import me.sahmad.planningpoker.routes.createPokerRoundRoutes
import me.sahmad.planningpoker.routes.createSessionRoutes
import me.sahmad.planningpoker.routes.createStaticRoutes

fun Application.configureRouting() {
    createStaticRoutes()
    createHomePageRoutes()
    createSessionRoutes()
    createPokerRoundRoutes()
}
