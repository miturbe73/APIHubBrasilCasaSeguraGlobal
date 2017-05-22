package com.enel.tinamica.api.models.casasegura.exceptions;

import java.io.Serializable;

/**
 * Created by user on 21/04/17.
 */


public class CasaSeguraError implements Serializable{

    private static final long serialVersionUID = -8013161496170679482L;


    private String component;
    private String code;
    private Throwable rootCause;
    private String message;

    public CasaSeguraError() {

    }

    public CasaSeguraError(String component, String code, Throwable rootCause, String message) {
        this.component = component;
        this.code = code;
        this.rootCause = rootCause;
        this.message = message;
    }

    public CasaSeguraError(String component, String code, String message, Throwable rootCause ) {
        this.component = component;
        this.code = code;
        this.rootCause = rootCause;
        this.message = message;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Throwable getRootCause() {
        return rootCause;
    }

    public void setRootCause(Throwable rootCause) {
        this.rootCause = rootCause;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CasaSeguraError that = (CasaSeguraError) o;

        if (component != null ? !component.equals(that.component) : that.component != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (rootCause != null ? !rootCause.equals(that.rootCause) : that.rootCause != null) return false;
        return message != null ? message.equals(that.message) : that.message == null;
    }


    @Override
    public int hashCode() {
        int result = component != null ? component.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (rootCause != null ? rootCause.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CasaSeguraError{" +
                "component='" + component + '\'' +
                ", code='" + code + '\'' +
                ", rootCause=" + rootCause +
                ", message='" + message + '\'' +
                '}';
    }

}
/**
 * End of class
 */

