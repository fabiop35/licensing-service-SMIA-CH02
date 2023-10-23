package com.smia.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class UserContext {

    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN = "Authorization";
    public static final String USER_ID = "tmx-user-id";
    public static final String ORGANIZATION_ID = "tmx-organization-id";

    private static final ThreadLocal<String> correlationId = new ThreadLocal<String>();
    private static final ThreadLocal<String> authToken = new ThreadLocal<String>();
    private static final ThreadLocal<String> userId = new ThreadLocal<String>();
    private static final ThreadLocal<String> organizationId = new ThreadLocal<String>();

    public static String getCorrelationId() {
        return correlationId.get();
    }

    public static void setCorrelationId(String cid) {
        correlationId.set(cid);
    }

    public static String getAuthToken() {
        return authToken.get();
    }

    public static void setAuthToken(String token) {
        authToken.set(token);
    }

    public static String getUserId() {
        return userId.get();
    }

    public static void setUserId(String user) {
        userId.set(user);
    }

    public static String getOrganizationId() {
        return organizationId.get();
    }

    public static void setOrganizationId(String id) {
        organizationId.set(id);
    }

    public static HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(CORRELATION_ID, getCorrelationId());

        return httpHeaders;
    }

}
