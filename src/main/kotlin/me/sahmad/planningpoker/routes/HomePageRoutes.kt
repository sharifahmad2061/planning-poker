package me.sahmad.planningpoker.routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
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
import kotlinx.html.h3
import kotlinx.html.head
import kotlinx.html.p
import kotlinx.html.submitInput
import kotlinx.html.textInput
import kotlinx.html.title

fun Application.createHomePageRoutes() {
    routing {
        homePageRoute()
    }
}

fun Route.homePageRoute() {
    route("/") {
        get {
            call.respondHtml(HttpStatusCode.OK) {
                head {
                    title {
                        +"Planning Poker"
                    }
                }
                body {
                    h3 {
                        +"Create a new Session"
                    }
                    form (action = "/session", method = FormMethod.post, encType = FormEncType.applicationXWwwFormUrlEncoded) {
                        p {
                            +"Name:"
                            textInput (name = "name")
                         }
                        p {
                            submitInput { value = "Create Session" }
                        }
                     }
                 }
            }
        }
    }
}