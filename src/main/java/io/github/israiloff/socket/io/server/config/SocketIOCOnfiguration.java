package io.github.israiloff.socket.io.server.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.corundumstudio.socketio.SocketIOServer;

/**
 * Socket IO main configurations.
 */
@Configuration
@EnableConfigurationProperties({ SocketIOProperties.class })
public class SocketIOCOnfiguration {

    /**
     * Socket IO server bean.
     *
     * @param properties Socket IO server properties.
     * @return Socket IO server.
     */
    @Bean
    SocketIOServer socketIOServer(SocketIOProperties properties) {
        var config = new com.corundumstudio.socketio.Configuration();
        config.setHostname(properties.host());
        config.setPort(properties.port());
        return new SocketIOServer(config);
    }
}
