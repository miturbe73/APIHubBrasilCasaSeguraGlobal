package com.enel.tinamica.api.models.casasegura.model;

import java.io.Serializable;

/**
 * Created by user on 17/05/17.
 */
public class BeanProjectModelFilter implements Serializable {

    private static final long serialVersionUID = -9179266935289412517L;


    public BeanProjectModelFilter(){}

    private EventFilter filter;

    public BeanProjectModelFilter(EventFilter filter) {
        this.filter = filter;
    }

    public EventFilter getFilter() {
        return filter;
    }

    public void setFilter(EventFilter filter) {
        this.filter = filter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BeanProjectModelFilter that = (BeanProjectModelFilter) o;

        return filter.equals(that.filter);
    }

    @Override
    public int hashCode() {
        return filter.hashCode();
    }

    @Override
    public String toString() {
        return "BeanProjectModelFilter{" +
                "filter=" + filter +
                '}';
    }
}
