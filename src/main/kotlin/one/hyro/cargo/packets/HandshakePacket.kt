package one.hyro.cargo.packets

import one.hyro.cargo.types.VarInt

data class HandshakePacket(
    val protocolVersion: VarInt,
    val serverAddress: String,
    val serverPort: UShort,
    val nextState: VarInt
)
