package io.github.israiloff.socket.io.server.config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.protocol.JacksonJsonSupport;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

/**
 * Socket IO main configurations.
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({SocketIOProperties.class})
public class SocketIOCOnfiguration {

    public static final String SOCKET_DATE_MODULE = "ioGithubIsrailoffSocketDateModule";

    /**
     * Socket IO server bean.
     *
     * @param properties Socket IO server properties.
     * @param module Date module.
     * @return Socket IO server.
     */
    @Bean
    SocketIOServer socketIOServer(SocketIOProperties properties,
            @Qualifier(SOCKET_DATE_MODULE) SimpleModule module) {
        log.info("Creating socket IO server bean with properties: {}", properties);
        var config = new com.corundumstudio.socketio.Configuration();
        config.setHostname(properties.host());
        config.setPort(properties.port());
        var jsonSupport = new JacksonJsonSupport(new JavaTimeModule(), module);
        config.setJsonSupport(jsonSupport);
        return new SocketIOServer(config);
    }

    /**
     * Creates simple module for date serialization.
     *
     * @return Instance of simple module.
     */
    @Bean(SOCKET_DATE_MODULE)
    SimpleModule simpleModule() {
        var module = new SimpleModule();
        var formatter = DateTimeFormatter.ISO_DATE_TIME;

        module.addSerializer(LocalDateTime.class, new JsonSerializer<>() {
            @Override
            public void serialize(LocalDateTime time, JsonGenerator gen,
                    SerializerProvider provider) throws IOException {
                gen.writeString(time.format(formatter));
            }
        });

        module.addDeserializer(LocalDateTime.class, new JsonDeserializer<>() {
            @Override
            public LocalDateTime deserialize(JsonParser parser, DeserializationContext context)
                    throws IOException {
                return LocalDateTime.parse(parser.getValueAsString(), formatter);
            }
        });

        return module;
    }
}
