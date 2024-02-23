package me.sahmad.planningpoker.routes

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.requestvalidation.RequestValidation
import io.ktor.server.plugins.requestvalidation.ValidationResult
import io.ktor.server.application.call
import io.ktor.server.html.respondHtml
import io.ktor.server.request.receive
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respond
import io.ktor.server.response.respondRedirect
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
            val name: String? = call.receiveParameters()["name"]
            if (name.isNullOrEmpty() or name!!.isBlank()) call.respondRedirect("/", permanent = false)
            else {
                val sessionId = UUID.randomUUID()
                val userId = UUID.randomUUID()
                val user = User(name = name, userId = userId)
                SessionStorage.sessions[sessionId] = listOf(user)
                call.respondRedirect("/session/${sessionId}", permanent = false)
            }
            return@post
        }
        get ("/{sessionId}") {
            val sessionId:UUID = UUID.fromString(call.parameters["sessionId"])
            if (SessionStorage.sessions.containsKey(sessionId)) call.respondHtml {

             }
            else call.respondRedirect("/", permanent = false) // session doesn't exist
            return@get
        }
    }
}
