package me.sahmad.planningpoker.website.routes

import io.ktor.http.HttpStatusCode
import io.ktor.http.parametersOf
import io.ktor.http.path
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.html.respondHtml
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import io.ktor.server.sessions.get
import io.ktor.server.sessions.sessions
import java.util.UUID
import kotlinx.html.FormEncType
import kotlinx.html.FormMethod
import kotlinx.html.body
import kotlinx.html.form
import kotlinx.html.h1
import kotlinx.html.hiddenInput
import kotlinx.html.p
import kotlinx.html.submitInput
import me.sahmad.planningpoker.common.models.User

fun Application.createSessionRoutes() {
    routing {
        sessionRoutes()
    }
}

fun Route.sessionRoutes() {
    route("/session") {
        get {
            val user: User? = call.sessions.get<User>()
            if (user == null) call.respondRedirect("/", permanent = false)
            else {
                call.respondHtml (HttpStatusCode.OK) {
                    body {
                        h1 {
                            +"New Session"
                        }
                        form ("localhost:8080/session", encType = FormEncType.applicationXWwwFormUrlEncoded, method = FormMethod.post) {
                            hiddenInput (name = "user") { value = user.id.toString() }
                            p {
                                submitInput { value = "Start" }
                             }
                         }
                     }
                }
            }
        }
        get ("/{sessionId}") {
            val sessionId: UUID = UUID.fromString(call.parameters["sessionId"])
            val user: User? = call.sessions.get<User>()
            if (user == null) call.respondRedirect(permanent = false) {
                path("/login")
                parametersOf("origin", "/session/${sessionId}")
            } else {
                call.respondRedirect("/poker", permanent = false)
            }
        }
    }
}