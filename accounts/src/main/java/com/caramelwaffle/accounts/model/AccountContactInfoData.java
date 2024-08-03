package com.caramelwaffle.accounts.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "account")
public record AccountContactInfoData(String message, Map<String, String> contactDetail) {

}
