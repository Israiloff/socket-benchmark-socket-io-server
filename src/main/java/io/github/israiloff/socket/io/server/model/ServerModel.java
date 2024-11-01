package io.github.israiloff.socket.io.server.model;

import java.io.Serializable;

/**
 * Server data model.
 *
 * @param data Plain text data.
 */
public record ServerModel(String data) implements Serializable {
}
