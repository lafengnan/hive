package com.lafengnan.hive;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static com.lafengnan.hive.Server.Status.ACTIVE;
import static com.lafengnan.hive.Server.Status.CLOSED;
import static com.lafengnan.hive.Server.Status.RUNNING;

@SpringBootApplication
@Setter
@Getter
public class HiveServer implements Server{
    private static final Logger logger = LoggerFactory.getLogger(HiveServer.class);

    private SpringApplication application;
    private ConfigurableApplicationContext context;

    public static void main(String... args) {
        HiveServer server = new HiveServer();
        server.setApplication(new SpringApplication(HiveServer.class));
        if (!server.start(args).equals(ACTIVE)) {
            logger.error("HiveServer starting failed!");
            System.exit(-1);
        }
    }

    @Override
    public Status start(String... args) {
        context = application.run();
        return context.isActive()?Status.ACTIVE:Status.CLOSED;
    }

    @Override
    public Status stop(String... args) {
        context.stop();
        return context.isActive()?Status.ACTIVE:Status.CLOSED;
    }

    @Override
    public Status status() {
        return context.isRunning()?RUNNING: context.isActive()?ACTIVE:CLOSED;
    }

    @Override
    public HealthIndicator health() {
        return HealthIndicator.GREEN;
    }


}
