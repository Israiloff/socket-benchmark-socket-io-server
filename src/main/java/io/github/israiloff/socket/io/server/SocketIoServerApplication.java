package io.github.israiloff.socket.io.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "io.github.israiloff")
public class SocketIoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocketIoServerApplication.class, args);
    }

}
