package me.sahmad.planningpoker.routes

import io.ktor.http.HttpStatusCodeimport io.ktor.http.HttpStatusCodeimport
import io.ktor.server.response.respondRedirect
import io.ktor.server.sessions.sessions
import io.ktor.server.sessions.set
import java.util.UUID
import me.sahmad.planningpoker.models.User io.ktor.http.HttpStatusCodeimport
import io.ktor.server.application.Application
import io.ktor.server.html.respondHtml
import kotlinx.html.FormEncType
import kotlinx.html.FormMethod
import kotlinx.html.body
import kotlinx.html.form
import kotlinx.html.h3
import kotlinx.html.p
import kotlinx.html.submitInput
import kotlinx.html.textInput
import me.sahmad.planningpoker.models.LoginResponse io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Application.createLoginRoutes() {

}

fun Route.loginRoutes() {
    route("/login") {
        get {
            call.respondHtml {
                body {
                    h3 {
                        +"Login"
                     }
                     form(action = "/login", encType = FormEncType.applicationXWwwFormUrlEncoded, method = FormMethod.post) {
                         p {
                             textInput { +"name" }
                         }
                         p {
                             submitInput { value = "Submit" }
                         }
                     }
                 }
             }
        }
        post {
            val name: String? = call.receiveParameters()["name"]
            if (name.isNullOrEmpty() or name!!.isBlank()) call.respond(HttpStatusCode.BadRequest) {
                LoginResponse.Error("name can't be empty")
            } else {
                val user = User(name = name, UUID.randomUUID())
                call.sessions.set(user)
                call.respondRedirect("")
            }
        }
    }
}