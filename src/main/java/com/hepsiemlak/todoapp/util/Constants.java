package com.hepsiemlak.todoapp.util;

public class Constants {

    public static final String APP_ROOT = "/api";

    public static final CharSequence JWT_SECRET_TOKEN = "F2UaNHyCWUyDhlu50duV0WMQX+6OGJFhY5l7sA8hB24=";

    public static final String[] WHITE_LIST_URLS = {
            "/api/auth/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/api/users/create"             // Must be deleted after fixing the JWT bug
    };
}
