package com.enel.tinamica.api.models.casasegura.model;

import java.io.Serializable;

/**
 * Created by user on 3/05/17.
 */
public class EventHeader implements Serializable {

    private static final long serialVersionUID = -9179215935289412413L;

    private String project;
    private String country;
    private String model;
    private String tableOut;
    private String tableIn;
    private String fecreaevent;

    public EventHeader() {
    }

    public EventHeader(String project, String country, String model, String tableOut, String tableIn, String fecreaevent) {
        this.project = project;
        this.country = country;
        this.model = model;
        this.tableOut = tableOut;
        this.tableIn = tableIn;
        this.fecreaevent = fecreaevent;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTableOut() {
        return tableOut;
    }

    public void setTableOut(String tableOut) {
        this.tableOut = tableOut;
    }

    public String getTableIn() {
        return tableIn;
    }

    public void setTableIn(String tableIn) {
        this.tableIn = tableIn;
    }

    public String getFecreaevent() {
        return fecreaevent;
    }

    public void setFecreaevent(String fecreaevent) {
        this.fecreaevent = fecreaevent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventHeader that = (EventHeader) o;

        if (!project.equals(that.project)) return false;
        if (!country.equals(that.country)) return false;
        if (!model.equals(that.model)) return false;
        if (!tableOut.equals(that.tableOut)) return false;
        if (!tableIn.equals(that.tableIn)) return false;
        return fecreaevent.equals(that.fecreaevent);
    }

    @Override
    public int hashCode() {
        int result = project.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + tableOut.hashCode();
        result = 31 * result + tableIn.hashCode();
        result = 31 * result + fecreaevent.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "EventHeader{" +
                "project='" + project + '\'' +
                ", country='" + country + '\'' +
                ", model='" + model + '\'' +
                ", tableOut='" + tableOut + '\'' +
                ", tableIn='" + tableIn + '\'' +
                ", fecreaevent='" + fecreaevent + '\'' +
                '}';
    }
}