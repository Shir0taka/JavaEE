package com.books.bookscrud.websocket;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;

@ServerEndpoint("/book-chat")
public class BookChatEndpoint {
    @OnOpen
    public void onOpen(Session session) {
        SessionHolder.addSession(session);
    }

    @OnClose
    public void onClose(Session session) {
        SessionHolder.removeSession(session);
    }
    @OnMessage
    public void handleTextMessage(String message, Session session) {
        System.out.println("Received message: " + message);

        broadcastMessage(message);
    }

    private void broadcastMessage(String message) {
        for (Session session : SessionHolder.getSessions()) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}