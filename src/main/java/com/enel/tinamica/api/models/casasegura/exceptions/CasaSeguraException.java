package com.enel.tinamica.api.models.casasegura.exceptions;

import java.io.Serializable;

/**
 * Created by user on 21/04/17.
 */
public class CasaSeguraException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -9013464492140669731L;

    private CasaSeguraError error = null;
    private String errorId;
    private String operationId;
    private int severity = ERROR;
    private Exception exception;

    /** Constantes que definen la gravedad del error **/
    /** Severidad <code>FATAL</code> indica una circunstancia ante la cual la aplicacion deberia detenerse **/

    public static final int FATAL = -100;

    /** Severidad <code>ERROR</code> indica un error en la aplicacion **/
    public static final int ERROR = -200;

    /** Severidad <code>WARNING</code> es un aviso de una determinada cicunstancia **/
    public static final int WARNING = -300;

    /** Severidad <code>INFORMATIVE</code> indica que es una excepcion informativa **/
    public static final int INFORMATIVE = -400;

    public CasaSeguraException(){

    }

    public CasaSeguraException(CasaSeguraError error, String errorId, String operationId, int severity, Exception exception) {
        this.error = error;
        this.errorId = errorId;
        this.operationId = operationId;
        this.severity = severity;
        this.exception = exception;
    }

    public CasaSeguraException(String message, CasaSeguraError error, String errorId, String operationId, int severity, Exception exception) {
        super(message);
        this.error = error;
        this.errorId = errorId;
        this.operationId = operationId;
        this.severity = severity;
        this.exception = exception;
    }

    public CasaSeguraException(String message, Throwable cause, CasaSeguraError error, String errorId, String operationId, int severity, Exception exception) {
        super(message, cause);
        this.error = error;
        this.errorId = errorId;
        this.operationId = operationId;
        this.severity = severity;
        this.exception = exception;
    }

    public CasaSeguraException(Throwable cause, CasaSeguraError error, String errorId, String operationId, int severity, Exception exception) {
        super(cause);
        this.error = error;
        this.errorId = errorId;
        this.operationId = operationId;
        this.severity = severity;
        this.exception = exception;
    }

    public CasaSeguraException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, CasaSeguraError error, String errorId, String operationId, int severity, Exception exception) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
        this.errorId = errorId;
        this.operationId = operationId;
        this.severity = severity;
        this.exception = exception;
    }

    public CasaSeguraError getError() {
        return error;
    }

    public void setError(CasaSeguraError error) {
        this.error = error;
    }

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CasaSeguraException that = (CasaSeguraException) o;

        if (severity != that.severity) return false;
        if (error != null ? !error.equals(that.error) : that.error != null) return false;
        if (errorId != null ? !errorId.equals(that.errorId) : that.errorId != null) return false;
        if (operationId != null ? !operationId.equals(that.operationId) : that.operationId != null) return false;
        return exception != null ? exception.equals(that.exception) : that.exception == null;
    }

    @Override
    public int hashCode() {
        int result = error != null ? error.hashCode() : 0;
        result = 31 * result + (errorId != null ? errorId.hashCode() : 0);
        result = 31 * result + (operationId != null ? operationId.hashCode() : 0);
        result = 31 * result + severity;
        result = 31 * result + (exception != null ? exception.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CasaSeguraException{" +
                "error=" + error +
                ", errorId='" + errorId + '\'' +
                ", operationId='" + operationId + '\'' +
                ", severity=" + severity +
                ", exception=" + exception +
                '}';
    }

}
/*
End of class
 */

