package com.books.bookscrud.controllers;

import com.books.bookscrud.beans.SessionCounterBean;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class SessionController implements Serializable {

    @EJB
    SessionCounterBean sessionCounterBean;

    private int counter;
    private boolean flag = false;

    public int getCounter() {
        if (!flag) {
            counter = sessionCounterBean.getNumberOfSessions();
            flag = true;
        }
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
