package io.github.israiloff.socket.io.server.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIOServer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Socket IO server command line runner.
 */
@Slf4j
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
        log.info("Starting socket IO server...");
        socketIOServer.start();
    }
}
