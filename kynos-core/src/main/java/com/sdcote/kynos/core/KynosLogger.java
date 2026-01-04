package com.sdcote.kynos.core;

import com.sdcote.kynos.api.Logger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class KynosLogger implements Logger {
    private final String name;
    private final KynosController controller;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public KynosLogger(Class<?> clazz, KynosController controller) {
        this.name = clazz.getSimpleName();
        this.controller = controller;
    }

    @Override
    public void info(String message) {
        if (controller.isLoggingEnabled()) {
            System.out.format("%s [INFO] %s: %s%n", LocalDateTime.now().format(formatter), name, message);
        }
    }

    @Override
    public void error(String message) {
        if (controller.isLoggingEnabled()) {
            System.err.format("%s [ERROR] %s: %s%n", LocalDateTime.now().format(formatter), name, message);
        }
    }

    @Override
    public void warn(String message) {
        if (controller.isLoggingEnabled()) {
            System.err.format("%s [WARN] %s: %s%n", LocalDateTime.now().format(formatter), name, message);
        }
    }

    @Override
    public void trace(String message) {
        if (controller.isLoggingEnabled()) {
            System.err.format("%s [TRACE] %s: %s%n", LocalDateTime.now().format(formatter), name, message);
        }
    }

    @Override
    public void debug(String message) {
        if (controller.isLoggingEnabled()) {
            System.err.format("%s [DEBUG] %s: %s%n", LocalDateTime.now().format(formatter), name, message);
        }
    }

    @Override
    public void error(String message, Throwable throwable) {
        if (controller.isLoggingEnabled()) {
            error(message);
            throwable.printStackTrace(System.err);
        }
    }
}