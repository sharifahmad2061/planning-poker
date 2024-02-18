package me.sahmad.planningpoker.routes

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import me.sahmad.planningpoker.models.CreateSessionRequest

fun Application.sessionRoutes() {
    routing {
        createSessionRoute()
     }
}

fun Route.createSessionRoute() {
    route("/session") {
        post {
            val request: CreateSessionRequest = call.receive<CreateSessionRequest>()

            return@post
         }
    }
}