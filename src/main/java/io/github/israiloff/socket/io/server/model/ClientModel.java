package io.github.israiloff.socket.io.server.model;

import java.io.Serializable;

/**
 * Client data model.
 *
 * @param data Plain text data.
 * @param time Processing time details.
 */
public record ClientModel(String data, Time time) implements Serializable {
}
