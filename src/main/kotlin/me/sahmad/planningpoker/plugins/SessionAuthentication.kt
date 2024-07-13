package me.sahmad.planningpoker.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.sessions.Sessions
import io.ktor.server.sessions.cookie
import me.sahmad.planningpoker.models.User

fun Application.configureSessionAuthentication() {
    install(Sessions) {
        cookie<User>("user") {
            cookie.httpOnly = true
            cookie.extensions["SameSite"] = "strict"
        }
    }
}
