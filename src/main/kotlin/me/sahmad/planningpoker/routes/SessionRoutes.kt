package me.sahmad.planningpoker.routes

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import java.util.UUID
import me.sahmad.planningpoker.models.CreateSessionRequest
import me.sahmad.planningpoker.models.CreateSessionResponse
import me.sahmad.planningpoker.models.SessionStorage
import me.sahmad.planningpoker.models.User

fun Application.createSessionRoutes() {
    routing {
        sessionRoutes()
    }
}

fun Route.sessionRoutes() {
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
        get ("/{sessionId}") {
            val sessionId: Result<UUID> = runCatching {
                UUID.fromString(call.parameters["sessionId"])
            }
            val userId: Result<UUID> = sessionId.mapCatching { _ -> UUID.randomUUID() }
            val user = User(userId = userId.getOrThrow())
        }
    }
}
