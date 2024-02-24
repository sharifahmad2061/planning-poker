package me.sahmad.planningpoker.website.routes

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
import kotlinx.html.p
import kotlinx.html.submitInput
import kotlinx.html.textInput

fun Application.createHomePageRoutes() {
    routing {
        homePageRoutes()
    }
}

fun Route.homePageRoutes() {
    route("/") {
        get {
            call.respondHtml {
                body {
                    h3 {
                        +"Login"
                    }
                    form(action = "localhost:8080/login", encType = FormEncType.applicationXWwwFormUrlEncoded, method = FormMethod.post) {
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
    }
}
