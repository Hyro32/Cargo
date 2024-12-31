package one.hyro.cargo.packets

import one.hyro.cargo.server.data.Players
import one.hyro.cargo.server.data.Version

data class StatusResponsePacket(
    val version: Version,
    val players: Players,
    val description: Description,
    val favicon: String? = null
)
