package com.books.bookscrud.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.books.bookscrud.websocket.BookChatEndpoint;

@Named
@RequestScoped
public class WebSocketBean {

    @Inject
    private BookChatEndpoint bookChatEndpoint;

    private String message;
    private String receivedMessage;

    @PostConstruct
    public void init() { }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceivedMessage() {
        return receivedMessage;
    }

    public void sendMessage() {
        bookChatEndpoint.handleTextMessage(message, null);
        receivedMessage = "Sent: " + message;

//        FacesContext.getCurrentInstance().addMessage(null,
//                new FacesMessage(FacesMessage.SEVERITY_INFO, "Message Sent!", null));
//        String script = "alert('Message Sent!');";
//        FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add(script);
    }
}