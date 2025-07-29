package com.epam.learn.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);
    private static final Properties properties = new Properties();

    static {
        String environment = System.getProperty("environment", "smoke");
        String configFile = String.format("config-%s.properties", environment);
        logger.info("Attempting to load configuration from: {}", configFile);
        try (InputStream resource = ConfigReader.class.getResourceAsStream("/" + configFile)) {
            if (resource == null) {
                logger.error("Configuration file {} not found in classpath", configFile);
                throw new RuntimeException("Configuration file " + configFile + " not found in classpath");
            }
            properties.load(resource);
            logger.info("Configuration loaded successfully from {}", configFile);
            logger.debug("Loaded properties: {}", properties);
        } catch (IOException e) {
            logger.error("Failed to load {}: {}", configFile, e.getMessage());
            throw new RuntimeException("Failed to load " + configFile, e);
        }
    }

    public static String getProperty(String key, String defaultValue) {
        String value = properties.getProperty(key, defaultValue);
        if (value == null && defaultValue == null) {
            logger.error("Property '{}' not found in configuration", key);
            throw new RuntimeException("Property '" + key + "' not found in configuration");
        }
        logger.debug("Retrieved property '{}': {}", key, value);
        return value;
    }

    public static String getLoginEmail() {
        return getProperty("login.email", null);
    }

    public static String getLoginPassword() {
        return getProperty("login.password", null);
    }

    public static String getBaseUrl() {
        String url = getProperty("base.url", "https://learn.epam.com");
        logger.info("Returning base URL: {}", url);
        return url;
    }
}