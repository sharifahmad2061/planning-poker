package me.sahmad.planningpoker.routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.patch
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import me.sahmad.planningpoker.models.SessionStorage
import me.sahmad.planningpoker.models.UpdateUserRequest
import me.sahmad.planningpoker.models.User

fun Application.createUserRoutes() {
    routing {
        userRoutes()
     }
}

fun Route.userRoutes() {
    route("/user") {
        patch {
            val request = call.receive<UpdateUserRequest>()
            val users: List<User>? = SessionStorage.sessions[request.sessionId]
            if (users == null) call.respond(HttpStatusCode.NoContent)
            else {
                val (matchedUsers, unMatchedUsers) = users.partition { it.userId == request.userId }
                if (matchedUsers.size > 1) {
                    call.respond(HttpStatusCode.ExpectationFailed, "multiple users exist with this user id")
                }
                val newUser = matchedUsers.first().copy(name = request.name)
                SessionStorage.sessions[request.sessionId] = unMatchedUsers + newUser
                call.respond(newUser)
                return@patch
            }
         }
    }
}