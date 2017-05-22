package com.enel.tinamica.api.models.casasegura.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 10/05/17.
 */
public class Eventbody implements Serializable{

    private static final long serialVersionUID = -3804618315289411251L;

    private List <Prediction> predictionList ;

    public Eventbody(){}

    public Eventbody(List<Prediction> predictionList) {
        this.predictionList = predictionList;
    }

    public List<Prediction> getPredictionList() {
        return predictionList;
    }

    public void setPredictionList(List<Prediction> predictionList) {
        this.predictionList = predictionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Eventbody eventbody = (Eventbody) o;

        return predictionList.equals(eventbody.predictionList);
    }

    @Override
    public int hashCode() {
        return predictionList.hashCode();
    }

    @Override
    public String toString() {
        return "Eventbody{" +
                "predictionList=" + predictionList +
                '}';
    }
}
