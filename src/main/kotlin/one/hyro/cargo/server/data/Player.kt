package one.hyro.cargo.server.data

import kotlinx.serialization.Serializable

@Serializable
data class Player(val name: String, val id: String)
