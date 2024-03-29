package me.sahmad.planningpoker.routes

import io.ktor.http.HttpStatusCode
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
import io.ktor.server.sessions.get
import io.ktor.server.sessions.sessions
import io.ktor.server.sessions.set
import java.util.UUID
import kotlinx.html.FormEncType
import kotlinx.html.FormMethod
import kotlinx.html.body
import kotlinx.html.form
import kotlinx.html.h3
import kotlinx.html.p
import kotlinx.html.submitInput
import kotlinx.html.textInput
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
            if (name.isNullOrEmpty() or name!!.isBlank()) call.respond(HttpStatusCode.BadRequest) {
                CreateSessionResponse.Error("name can't be empty")
            }
            else {
                val sessionId = UUID.randomUUID()
                val userId = UUID.randomUUID()
                val user = User(name = name, userId = userId)
                call.sessions.set(user)
                SessionStorage.sessions[sessionId] = listOf(user)
                call.respond(HttpStatusCode.OK) {
                    CreateSessionResponse.Success(sessionId)
                }
            }
            return@post
        }
        post("/user") {
            val formParameters = call.receiveParameters()
            val name: String? = formParameters["name"]
            val sessionId: String? = formParameters["session-id"]
            if (name.isNullOrEmpty() or sessionId.isNullOrEmpty() or name!!.isBlank() or sessionId!!.isBlank()) {
                call.respondHtml (HttpStatusCode.BadRequest) {
                    +"name cannot be empty"
                 }
            } else {

            }
            return@post
        }
        get ("/{sessionId}") {
            val sessionId:UUID = UUID.fromString(call.parameters["sessionId"])
            val user: User? = call.sessions.get<User>()
            if (user == null) call.respondHtml(HttpStatusCode.Unauthorized) {
                body {
                    form(action = "/${sessionId}", encType = FormEncType.applicationXWwwFormUrlEncoded, method = FormMethod.post) {
                        p {
                            textInput { +"name" }
                         }
                         p {
                             submitInput { value = "Submit" }
                         }
                    }
                 }
            }
            else {

            }
            return@get
        }
        post ("/{sessionId}") {
            val sessionId:UUID = UUID.fromString(call.parameters["sessionId"])
            if (!SessionStorage.sessions.containsKey(sessionId)) call.respondRedirect("/session", permanent = false)
            val name: String? = call.receiveParameters()["name"]
            if (name.isNullOrEmpty() or name!!.isBlank()) call.respondRedirect("/${sessionId}")
            else {
                val user = User(name = name, userId = UUID.randomUUID())
                call.sessions.set(user)
                SessionStorage.sessions[sessionId] = SessionStorage.sessions[sessionId]!! + user
                call.respondRedirect("/${sessionId}", permanent = false)
            }
        }
    }
}
