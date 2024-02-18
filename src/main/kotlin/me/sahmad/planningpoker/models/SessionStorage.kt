package me.sahmad.planningpoker.models

import java.util.UUID

object SessionStorage {
    val sessions: Map<UUID, List<User>> = mutableMapOf()
}