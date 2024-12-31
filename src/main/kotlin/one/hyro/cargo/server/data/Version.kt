package one.hyro.cargo.server.data

import kotlinx.serialization.Serializable

@Serializable
data class Version(val name: String, val protocol: Int)
