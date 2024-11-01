package io.github.israiloff.socket.io.server.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Time interval details.
 *
 * @param started Time started.
 * @param ended   Time ended.
 */
public record Time(LocalDateTime started, LocalDateTime ended) implements Serializable {
}
