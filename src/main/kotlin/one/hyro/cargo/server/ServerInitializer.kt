package one.hyro.cargo.server

import io.netty5.channel.ChannelInitializer
import io.netty5.channel.socket.SocketChannel
import one.hyro.cargo.connection.Connection
import one.hyro.cargo.packets.PacketDecoder
import one.hyro.cargo.packets.StateHandler

class ServerInitializer : ChannelInitializer<SocketChannel>() {
    override fun initChannel(channel: SocketChannel) {
        val pipeline = channel.pipeline()
        val connection = Connection()

        pipeline.addLast(PacketDecoder())
        pipeline.addLast(StateHandler(connection))
    }
}
