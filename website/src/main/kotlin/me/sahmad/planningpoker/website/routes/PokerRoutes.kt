package me.sahmad.planningpoker.website.routes

import io.ktor.server.application.Application
import io.ktor.server.routing.Route
import io.ktor.server.routing.routing
import io.ktor.server.websocket.webSocket

fun Application.createPokerRoutes() {
    routing {
        pokerRoutes()
    }
}

fun Route.pokerRoutes() {
    webSocket("/poker") {
    }
}
