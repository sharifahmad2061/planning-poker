package me.sahmad.planningpoker.routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.html.respondHtml
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
import me.sahmad.planningpoker.models.CreateSessionResponse
import me.sahmad.planningpoker.models.SessionStorage
import me.sahmad.planningpoker.models.User
import java.util.UUID
import kotlinx.html.body
import kotlinx.html.h3
import kotlinx.html.head
import kotlinx.html.p
import kotlinx.html.script
import kotlinx.html.title
import me.sahmad.planningpoker.models.Session

fun Application.createSessionRoutes() {
    routing {
        sessionRoutes()
    }
}

fun Route.sessionRoutes() {
    route("/session") {
        post {
            val parameters = call.receiveParameters()
            val actionType = parameters["submit"]
            when(actionType) {
                "Create Session" -> {
                    val name: String? = parameters["name"]
                    if (name.isNullOrEmpty() or name!!.isBlank()) {
                        call.respond(HttpStatusCode.BadRequest) {
                            CreateSessionResponse.Error("name can't be empty")
                        }
                    } else {
                        val sessionId = UUID.randomUUID()
                        val userId = UUID.randomUUID()
                        val user = User(name = name, userId = userId, isLeader = true)
                        call.sessions.set(user)
                        SessionStorage.sessions[sessionId] = Session(users = listOf(user))
                        call.respondRedirect("/session/$sessionId")
                    }
                }
                "Join Session" -> {
                    val name: String? = parameters["name"]
                    val sessionId: String? = parameters["sessionId"]
                    if (name.isNullOrEmpty() or sessionId.isNullOrEmpty() or name!!.isBlank() or sessionId!!.isBlank()) {
                        call.respondRedirect("/")
                    } else {
                        val sessionId = UUID.fromString(sessionId)
                        val session = SessionStorage.sessions[sessionId]
                        if (session == null) {
                            call.respondRedirect("/")
                        } else {
                            val userId = UUID.randomUUID()
                            val user = User(name = name, userId = userId)
                            call.sessions.set(user)
                            SessionStorage.sessions[sessionId] = session.copy(users = session.users + user)
                            call.respondRedirect("/session/$sessionId")
                        }
                    }
                }
            }
            return@post
        }
        get("/{sessionId}") {
            val sessionId: UUID = UUID.fromString(call.parameters["sessionId"])
            val session: Session? = SessionStorage.sessions[sessionId]
            val user: User? = call.sessions.get<User>()
            val userHasJoinedTheSession: Boolean = session?.users?.contains(user) ?: false
            if (session == null || user == null || !userHasJoinedTheSession) {
                call.respondRedirect("/")
            } else {
                call.respondHtml(HttpStatusCode.OK) {
                    head {
                        title { +"Planning Poker" }
                        script { src = "/static/js/main.js" }
                     }
                    body {
                        h3 {
                            +"Session: $sessionId"
                         }
                         p {
                             +"Users: ${session.users.joinToString { it.name }}"
                          }
                     }
                }
            }
            return@get
        }
    }
}
