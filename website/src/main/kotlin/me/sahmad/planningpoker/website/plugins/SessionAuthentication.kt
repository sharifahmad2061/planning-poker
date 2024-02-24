package me.sahmad.planningpoker.website.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.sessions.Sessions
import io.ktor.server.sessions.cookie
import me.sahmad.planningpoker.common.models.User

fun Application.configureSessionAuthentication() {
    install(Sessions) {
        cookie<User>("user") {
            cookie.httpOnly = true
            cookie.extensions["SameSite"] = "strict"
        }
    }
}