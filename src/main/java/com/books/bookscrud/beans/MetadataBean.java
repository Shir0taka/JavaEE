package com.books.bookscrud.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.lang.reflect.Field;

@Named("MetadataBean")
@RequestScoped
public class MetadataBean {

    private final Class<CalculationBean> clazz = CalculationBean.class;

    public String getCalculationBeanClassName() {
        return clazz.getName();
    }

    public Field[] getFields() {
        return clazz.getDeclaredFields();
    }
}
