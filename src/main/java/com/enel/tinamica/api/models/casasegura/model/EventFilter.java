package com.enel.tinamica.api.models.casasegura.model;

import java.io.Serializable;

/**
 * Created by user on 17/05/17.
 */
public class EventFilter implements Serializable {

    private static final long serialVersionUID = -9179315935289412413L;

    public EventFilter(){}

    private String project;
    private String country;
    private String type_event;
    private String entity;
    private String model;
    private String fecCreaEvent;

    public EventFilter(String project, String country, String type_event, String entity, String model, String fecCreaEvent) {
        this.project = project;
        this.country = country;
        this.type_event = type_event;
        this.entity = entity;
        this.model = model;
        this.fecCreaEvent = fecCreaEvent;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType_event() {
        return type_event;
    }

    public void setType_event(String type_event) {
        this.type_event = type_event;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFecCreaEvent() {
        return fecCreaEvent;
    }

    public void setFecCreaEvent(String fecCreaEvent) {
        this.fecCreaEvent = fecCreaEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventFilter that = (EventFilter) o;

        if (!project.equals(that.project)) return false;
        if (!country.equals(that.country)) return false;
        if (!type_event.equals(that.type_event)) return false;
        if (!entity.equals(that.entity)) return false;
        if (!model.equals(that.model)) return false;
        return fecCreaEvent.equals(that.fecCreaEvent);
    }

    @Override
    public int hashCode() {
        int result = project.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + type_event.hashCode();
        result = 31 * result + entity.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + fecCreaEvent.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "EventFilter{" +
                "project='" + project + '\'' +
                ", country='" + country + '\'' +
                ", type_event='" + type_event + '\'' +
                ", entity='" + entity + '\'' +
                ", model='" + model + '\'' +
                ", fecCreaEvent='" + fecCreaEvent + '\'' +
                '}';
    }
}
