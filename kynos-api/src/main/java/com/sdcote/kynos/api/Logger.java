package com.sdcote.kynos.api;

public interface Logger {
    void info(String message);
    void error(String message, Throwable throwable);
    void error(String message);
    void trace(String message);
    void warn(String message);
}