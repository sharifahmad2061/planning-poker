package me.sahmad.planningpoker.website.routes

import io.ktor.http.HttpStatusCodeimport io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.html.respondHtml
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import kotlinx.html.FormEncType
import kotlinx.html.FormMethod
import kotlinx.html.body
import kotlinx.html.form
import kotlinx.html.h1
import kotlinx.html.p
import kotlinx.html.submitInput

fun Application.createSessionRoutes() {
    routing {
        sessionRoutes()
    }
}

fun Route.sessionRoutes() {
    route("/session") {
        get {
            call.respondHtml (HttpStatusCode.OK) {
                body {
                    h1 {
                        +"New Session"
                    }
                    form ("localhost:8080/session", encType = FormEncType.applicationXWwwFormUrlEncoded, method = FormMethod.post) {
                        p {
                            submitInput { value = "Start" }
                         }
                     }
                 }
             }
        }
    }
}