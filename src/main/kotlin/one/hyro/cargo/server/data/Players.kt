package one.hyro.cargo.server.data

import kotlinx.serialization.Serializable

@Serializable
data class Players(
    val max: Int,
    val online: Int,
    val sample: List<Player>
)
