package com.enel.tinamica.api.models.casasegura.model;

import java.io.Serializable;

/**
 * Created by user on 10/05/17.
 */
public class BeanProjectModel implements Serializable {

    private static final long serialVersionUID = -9179260935289412217L;

    private EventHeader header;
    private Eventbody body;

    public BeanProjectModel(){}

    public BeanProjectModel(Eventbody body, EventHeader header) {
        this.body = body;
        this.header = header;
    }

    public Eventbody getBody() {
        return body;
    }

    public void setBody(Eventbody body) {
        this.body = body;
    }

    public EventHeader getHeader() {
        return header;
    }

    public void setHeader(EventHeader header) {
        this.header = header;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BeanProjectModel that = (BeanProjectModel) o;

        if (!header.equals(that.header)) return false;
        return body.equals(that.body);
    }

    @Override
    public int hashCode() {
        int result = header.hashCode();
        result = 31 * result + body.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BeanProjectModel{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
