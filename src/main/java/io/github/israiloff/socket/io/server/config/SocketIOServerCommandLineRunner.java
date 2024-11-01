package io.github.israiloff.socket.io.server.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIOServer;

import lombok.RequiredArgsConstructor;

/**
 * Socket IO server command line runner.
 */
@Component
@RequiredArgsConstructor
public class SocketIOServerCommandLineRunner implements CommandLineRunner {

    private final SocketIOServer socketIOServer;

    /**
     * Start socket IO server.
     *
     * @param args Command line arguments.
     */
    @Override
    public void run(String... args) throws Exception {
        socketIOServer.start();
    }
}
