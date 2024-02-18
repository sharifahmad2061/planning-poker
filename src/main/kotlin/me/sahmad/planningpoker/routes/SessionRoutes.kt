package me.sahmad.planningpoker.routes

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import java.util.UUID
import me.sahmad.planningpoker.models.CreateSessionRequest
import me.sahmad.planningpoker.models.CreateSessionResponse
import me.sahmad.planningpoker.models.SessionStorage
import me.sahmad.planningpoker.models.User

fun Application.sessionRoutes() {
    routing {
        createSessionRoute()
     }
}

fun Route.createSessionRoute() {
    route("/session") {
        post {
            val request: CreateSessionRequest = call.receive<CreateSessionRequest>()
            val sessionId = UUID.randomUUID()
            val userId = UUID.randomUUID()
            val user = User(name = request.name, userId = userId)
            SessionStorage.sessions[sessionId] = listOf(user)
            call.respond(CreateSessionResponse(sessionId))
            return@post
         }
    }
}