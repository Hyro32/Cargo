package one.hyro.cargo.packets

import io.netty5.buffer.Buffer
import io.netty5.channel.ChannelHandlerContext
import io.netty5.handler.codec.ByteToMessageDecoder
import one.hyro.cargo.utils.readString
import one.hyro.cargo.utils.readVarInt

class PacketDecoder : ByteToMessageDecoder() {
    override fun decode(ctx: ChannelHandlerContext, buffer: Buffer) {
        if (buffer.readableBytes() < 4) return

        when (val packetId = buffer.readVarInt()) {
            0x00 -> decodeHandshake(ctx, buffer)
            else -> return println("Unknown packet id: $packetId")
        }
    }

    private fun decodeHandshake(ctx: ChannelHandlerContext, buffer: Buffer) {
        val protocolVersion = buffer.readVarInt()
        val serverAddress = buffer.readString()
        val serverPort = buffer.readUnsignedShort()
        val nextState = buffer.readVarInt()

        val handshakePacket = HandshakePacket(
            protocolVersion,
            serverAddress,
            serverPort,
            nextState
        )

        ctx.fireChannelRead(handshakePacket)
    }
}
