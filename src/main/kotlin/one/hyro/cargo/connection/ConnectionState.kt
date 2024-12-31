package one.hyro.cargo.connection

enum class ConnectionState {
    HANDSHAKING,
    STATUS,
    LOGIN,
    CONFIGURATION,
    PLAY
}