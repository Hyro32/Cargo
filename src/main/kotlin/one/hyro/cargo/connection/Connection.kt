package one.hyro.cargo.connection

import io.ktor.network.sockets.*

data class Connection(val socket: Socket) {
    private val input = socket.openReadChannel()
    private val output = socket.openWriteChannel()
    var state = ConnectionState.HANDSHAKING

    fun writePacket(): Nothing = TODO()

    fun receivePacket(): Nothing = TODO()
}
