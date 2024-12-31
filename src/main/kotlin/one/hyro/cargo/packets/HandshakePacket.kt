package one.hyro.cargo.packets

data class HandshakePacket(
    val protocolVersion: Int,
    val serverAddress: String,
    val serverPort: Int,
    val nextState: Int
)
