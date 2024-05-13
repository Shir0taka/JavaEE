package com.books.bookscrud.beans;
import jakarta.ejb.ConcurrencyManagement;
import jakarta.ejb.ConcurrencyManagementType;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;
import jakarta.annotation.PostConstruct;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class SessionCounterBean {
    private int numberOfSessions;

    @PostConstruct
    public void init() {
        numberOfSessions = 0;
    }

    public int getNumberOfSessions() {
        numberOfSessions++;
        return numberOfSessions;
    }

    public void setNumberOfSessions(int numberOfSessions) {
        this.numberOfSessions = numberOfSessions;
    }


    public void setCounter(int counter) {
        this.numberOfSessions = counter;
    }
}