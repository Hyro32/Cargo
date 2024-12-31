package one.hyro.cargo.utils

import io.netty5.buffer.Buffer
import java.nio.ByteBuffer

fun Buffer.readVarInt(): Int {
    var numRead = 0
    var result = 0
    var read: Byte

    do {
        read = this.readByte()
        val value = (read.toInt() and 0x7F)
        result = result or (value shl (7 * numRead))

        numRead++
        if (numRead > 5) {
            throw RuntimeException("VarInt is too big")
        }
    } while ((read.toInt() and 0x80) != 0)

    return result
}

fun Buffer.readVarLong(): Long {
    var numRead = 0
    var result: Long = 0
    var read: Byte

    do {
        read = this.readByte()
        val value = (read.toLong() and 0x7F)
        result = result or (value shl (7 * numRead))

        numRead++
        if (numRead > 10) {
            throw RuntimeException("VarLong is too big")
        }
    } while ((read.toInt() and 0x80) != 0)

    return result
}

fun Buffer.readString(): String {
    val length = readVarInt()
    val bytes = ByteBuffer.wrap(ByteArray(length))
    readBytes(bytes)
    return String(bytes.array(), Charsets.UTF_8)
}
