package com.enel.tinamica.api.models.casasegura.model;

import java.io.Serializable;

/**
 * Created by user on 10/05/17.
 */
public class Prediction implements Serializable{

    private static final long serialVersionUID = -9109260365289442453L;

    private String distribuidora;
    private String cpf;
    private String score;
    private String fechaejecucion;

    public Prediction(){}

    public Prediction(String distribuidora, String cpf, String score, String fechaejecucion) {
        this.distribuidora = distribuidora;
        this.cpf = cpf;
        this.score = score;
        this.fechaejecucion = fechaejecucion;
    }

    public String getDistribuidora() {
        return distribuidora;
    }

    public void setDistribuidora(String distribuidora) {
        this.distribuidora = distribuidora;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getFechaejecucion() {
        return fechaejecucion;
    }

    public void setFechaejecucion(String fechaejecucion) {
        this.fechaejecucion = fechaejecucion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prediction that = (Prediction) o;

        if (!distribuidora.equals(that.distribuidora)) return false;
        if (!cpf.equals(that.cpf)) return false;
        if (!score.equals(that.score)) return false;
        return fechaejecucion.equals(that.fechaejecucion);
    }

    @Override
    public int hashCode() {
        int result = distribuidora.hashCode();
        result = 31 * result + cpf.hashCode();
        result = 31 * result + score.hashCode();
        result = 31 * result + fechaejecucion.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Prediction{" +
                "distribuidora='" + distribuidora + '\'' +
                ", cpf='" + cpf + '\'' +
                ", score='" + score + '\'' +
                ", fechaejecucion='" + fechaejecucion + '\'' +
                '}';
    }
}
