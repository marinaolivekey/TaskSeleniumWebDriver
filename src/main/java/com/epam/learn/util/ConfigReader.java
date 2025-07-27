package com.epam.learn.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try {
            InputStream resource = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
            if (resource == null) {
                throw new RuntimeException("config.properties not found in classpath. Ensure it is in src/test/resources.");
            }
            properties.load(resource);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getLoginEmail() {
        String email = properties.getProperty("login.email");
        if (email == null) {
            throw new RuntimeException("login.email not found in config.properties");
        }
        return email;
    }

    public static String getLoginPassword() {
        String password = properties.getProperty("login.password");
        if (password == null) {
            throw new RuntimeException("login.password not found in config.properties");
        }
        return password;
    }
}