package com.gh.consumer.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketTools {

    private static Map<String, WebSocket> clients = new ConcurrentHashMap<>();

    public static Map<String, WebSocket> getClients() {
        return clients;
    }
}
