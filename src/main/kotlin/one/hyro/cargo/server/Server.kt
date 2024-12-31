package one.hyro.cargo.server

import io.netty5.bootstrap.ServerBootstrap
import io.netty5.channel.Channel
import io.netty5.channel.EventLoopGroup
import io.netty5.channel.MultithreadEventLoopGroup
import io.netty5.channel.nio.NioHandler
import io.netty5.channel.socket.nio.NioServerSocketChannel

class Server {
    private val bossGroup: EventLoopGroup = MultithreadEventLoopGroup(1, NioHandler.newFactory())
    private val workerGroup: EventLoopGroup = MultithreadEventLoopGroup(NioHandler.newFactory())
    private var channel: Channel? = null

    fun start() {
        try {
            val boostrap = ServerBootstrap()
            boostrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel::class.java)
                .childHandler(ServerInitializer())

            channel = boostrap.bind(25565).asStage().get()
            println("Cargo Minecraft Server started in the port 25565")
        } catch (e: Exception) {
            e.printStackTrace()
            stop()
        }
    }

    fun stop() {
        try {
            channel?.close()?.asStage()?.get()
            println("Cargo Server stopped")
        } finally {
            bossGroup.shutdownGracefully()
            workerGroup.shutdownGracefully()
        }
    }
}