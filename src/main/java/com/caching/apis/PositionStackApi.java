package com.caching.apis;

import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;

/**
 * The type Position stack api.
 */
@Slf4j
@Component
public class PositionStackApi {

    @Value("${api.key}")
    private String API_KEY;

    @Value("${api.base.url}")
    private String BASE_URL;

    private final StringBuilder requestUri;

    /**
     * Instantiates a new Position stack api.
     */
    public PositionStackApi() {
        requestUri = new StringBuilder();
    }

    private void addAccessKey() {
        requestUri.append("?access_key=").append(URLEncoder.encode(API_KEY, StandardCharsets.UTF_8));
    }

    /**
     * Forward position stack api.
     *
     * @param query the query
     * @return the position stack api
     */
    public PositionStackApi forward(String query) {
        requestUri.append(BASE_URL);
        requestUri.append("/forward");
        addAccessKey();
        requestUri.append("&query=").append(URLEncoder.encode(query, StandardCharsets.UTF_8));
        return this;
    }

    /**
     * Reverse position stack api.
     *
     * @param lat the lat
     * @param lng the lng
     * @return the position stack api
     */
    public PositionStackApi reverse(double lat, double lng) {
        requestUri.append(BASE_URL);
        requestUri.append("/reverse");
        addAccessKey();
        requestUri.append("&query=").append(lat).append(",").append(lng);
        log.error(requestUri.toString());
        return this;
    }

    /**
     * Build http request.
     *
     * @return the http request
     */
    public HttpRequest build() {
        log.info("{}", requestUri);
        return HttpRequest.newBuilder()
                .uri(URI.create(requestUri.toString()))
                .GET()
                .build();
    }

}
