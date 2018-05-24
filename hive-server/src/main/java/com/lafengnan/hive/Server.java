package com.lafengnan.hive;

interface Server {
    enum Status {
        CLOSED(-1),
        ACTIVE(0),
        RUNNING(1);
        private int value;
        Status(int value) {
            this.value = value;
        }
    }

    enum HealthIndicator {
        GREEN(0),
        YELLOW(1),
        RED(2);
        private int value;
        HealthIndicator(int value) {
            this.value = value;
        }
    }
    Status start(String... args);
    Status stop(String... args);
    Status status();
    HealthIndicator health();
}
