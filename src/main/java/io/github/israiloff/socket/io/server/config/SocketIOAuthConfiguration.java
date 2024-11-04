package io.github.israiloff.socket.io.server.config;

import org.springframework.stereotype.Component;
import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.AuthorizationResult;
import com.corundumstudio.socketio.HandshakeData;
import lombok.extern.slf4j.Slf4j;

/**
 * Socket IO authorization configuration.
 */
@Slf4j
@Component
public class SocketIOAuthConfiguration implements AuthorizationListener {

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthorizationResult getAuthorizationResult(HandshakeData data) {
        log.info("Authorization request from: {}", data.getSingleUrlParam("token"));
        return AuthorizationResult.SUCCESSFUL_AUTHORIZATION;
    }
}
