package one.hyro.cargo.packets

import io.netty5.channel.ChannelHandlerContext
import io.netty5.channel.SimpleChannelInboundHandler
import one.hyro.cargo.connection.Connection
import one.hyro.cargo.connection.ConnectionState

class StateHandler(private val connection: Connection) : SimpleChannelInboundHandler<Any>() {
    override fun messageReceived(ctx: ChannelHandlerContext, packet: Any) {
        when (connection.state) {
            ConnectionState.HANDSHAKING -> {
                if (packet !is HandshakePacket) return
                handleHandshaking(packet)
            }
            ConnectionState.STATUS -> handleStatus(ctx, packet)
            ConnectionState.LOGIN -> handleLogin(packet)
            ConnectionState.CONFIGURATION -> handleConfiguration(packet)
            ConnectionState.PLAY -> handlePlay(packet)
        }
    }

    private fun handleHandshaking(packet: HandshakePacket) {
        val nextState = when (packet.nextState) {
            1 -> ConnectionState.STATUS
            2 -> ConnectionState.LOGIN
            else -> return
        }

        connection.state = nextState
    }

    private fun handleStatus(ctx: ChannelHandlerContext, packet: Any) {
        // Handle status packets
    }

    private fun handleLogin(packet: Any?) {
        // Handle login packets
    }

    private fun handleConfiguration(packet: Any?) {
        // Handle configuration packets
    }

    private fun handlePlay(packet: Any?) {
        // Handle play packets
    }

    override fun channelExceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        cause?.printStackTrace()
        ctx?.close()
    }
}
