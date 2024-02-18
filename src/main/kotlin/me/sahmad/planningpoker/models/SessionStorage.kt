package me.sahmad.planningpoker.models

import java.util.UUID

object SessionStorage {
    val sessions: MutableMap<UUID, List<User>> = mutableMapOf()
}