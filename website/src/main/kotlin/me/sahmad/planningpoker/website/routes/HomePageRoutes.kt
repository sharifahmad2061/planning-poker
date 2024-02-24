package me.sahmad.planningpoker.website.routes

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.html.respondHtml
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import io.ktor.server.sessions.sessions
import io.ktor.server.sessions.set
import java.util.UUID
import kotlinx.html.FormEncType
import kotlinx.html.FormMethod
import kotlinx.html.body
import kotlinx.html.form
import kotlinx.html.h3
import kotlinx.html.hiddenInput
import kotlinx.html.p
import kotlinx.html.submitInput
import kotlinx.html.textInput
import me.sahmad.planningpoker.common.models.User

fun Application.createHomePageRoutes() {
    routing {
        homePageRoutes()
    }
}

fun Route.homePageRoutes() {
    route("/") {
        get {
            val origin: String? = call.parameters["origin"]
            call.respondHtml {
                body {
                    h3 {
                        +"Login"
                    }
                    form(action = "/login", encType = FormEncType.applicationXWwwFormUrlEncoded, method = FormMethod.post) {
                        p {
                            textInput { +"name" }
                        }
                        if (!origin.isNullOrBlank()) {
                            p {
                                hiddenInput (name = "origin") { value = origin }
                            }
                        }
                        p {
                            submitInput { value = "Submit" }
                        }
                    }
                }
            }
            return@get
        }
        post {
            val name: String? = call.receiveParameters()["name"]
            if (name.isNullOrBlank()) call.respondRedirect("/", permanent = false)
            else {
                val user: User = User(name = name, id = UUID.randomUUID())
                call.sessions.set(user)
            }
            val origin: String? = call.receiveParameters()["origin"]
            if (!origin.isNullOrBlank()) call.respondRedirect(origin, permanent = false)
            call.respondRedirect("/session", permanent = false)
            return@post
        }
    }
}
