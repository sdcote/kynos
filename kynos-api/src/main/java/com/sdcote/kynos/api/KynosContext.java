package com.sdcote.kynos.api;

public interface KynosContext {
    void goHome();
    void updateStatusBarMessage(String message);

    // Logging control
    void setLoggingEnabled(boolean enabled);
    boolean isLoggingEnabled();
    Logger getLogger(Class<?> clazz);

    // Future methods for GPS, USB, and System events
}