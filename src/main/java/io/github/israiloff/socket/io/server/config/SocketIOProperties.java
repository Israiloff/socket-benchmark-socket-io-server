package io.github.israiloff.socket.io.server.config;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Socket IO main configuration properties.
 *
 * @param host Socket IO server host.
 * @param port Socket IO server port.
 */
@ConfigurationProperties(prefix = "application.socket.io")
public record SocketIOProperties(String host, int port) implements Serializable {
}
