package com.sdcote.kynos.api;

public interface KynosContext {
    void goHome();
    void updateStatusBarMessage(String message);
    // Future methods for GPS, USB, and System events
}