package com.barry.tennis.write.hexagon.models;

import java.time.LocalDateTime;

public class DeterministicDateTimeProvider implements DateTimeProvider {

    private final LocalDateTime localDateTime;

    public DeterministicDateTimeProvider(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public LocalDateTime now() {
        return localDateTime;
    }
}
