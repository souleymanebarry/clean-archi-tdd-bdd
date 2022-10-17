package com.barry.tennis.write.hexagon.models;

import java.time.LocalDateTime;

public class RealDateTimeProvider implements DateTimeProvider {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
