package io.github.israiloff.socket.io.server.socket;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

import io.github.israiloff.socket.io.server.model.ClientModel;
import io.github.israiloff.socket.io.server.model.ServerModel;
import io.github.israiloff.socket.io.server.model.Time;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Socket IO module.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SocketIOModule {

    private final SocketIOServer socketIOServer;

    @PostConstruct
    void postConstruct() {
        socketIOServer.addConnectListener(onConnect());
        socketIOServer.addDisconnectListener(onDisconnect());
        socketIOServer.addEventListener("echo", ServerModel.class, onEcho());
        socketIOServer.addEventListener("delayed", ServerModel.class, onDelayed());
    }

    private DataListener<ServerModel> onEcho() {
        return (client, data, ackSender) -> {
            var started = LocalDateTime.now();
            log.info("Received data: {}", data);
            var clientModel = new ClientModel(data.data(), new Time(started, LocalDateTime.now()));
            client.sendEvent("echoResult", clientModel);
        };
    }

    private DataListener<ServerModel> onDelayed() {
        return (client, data, ackSender) -> {
            var started = LocalDateTime.now();
            log.info("on delayed started for data: {}", data);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.error("Error occurred while delaying data: {}", e.getMessage());
            }

            var clientModel = new ClientModel(data.data(), new Time(started, LocalDateTime.now()));
            client.sendEvent("delayedCompleted", clientModel);
        };
    }

    private ConnectListener onConnect() {
        return client -> {
            log.info("Client connected: '{}'", client.getSessionId());
        };
    }

    private DisconnectListener onDisconnect() {
        return client -> {
            log.info("Client disconnected: '{}'", client.getSessionId());
        };
    }
}
