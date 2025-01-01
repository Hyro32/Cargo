package one.hyro.cargo.server

import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import one.hyro.cargo.packets.PacketsHandler

class Server {
    private lateinit var server: ServerSocket

    fun start() {
        runBlocking(SupervisorJob()) {
            server = aSocket(ActorSelectorManager(Dispatchers.IO))
                .tcp()
                .bind("0.0.0.0", 25565)

            while (true) {
                println("Server started in the port 25565")
                val socket: Socket = server.accept()
                launch { PacketsHandler.handle(socket) }
            }
        }
    }

    fun stop() {
        server.close()
        println("Cargo Server stopped")
    }
}
