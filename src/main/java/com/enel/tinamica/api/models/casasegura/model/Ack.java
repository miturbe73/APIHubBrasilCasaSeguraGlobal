package com.enel.tinamica.api.models.casasegura.model;

/**
 * Created by user on 24/04/17.
 */
import java.io.Serializable;

public class Ack implements Serializable{

    private static final long serialVersionUID = -3804111765289442413L;

    private boolean isOk;
    private String msgAck;
    private String user;
    private String service;
    private String component;
    private long eventDate;
    private String stremingEvent;

    public Ack() {

    }

    public Ack(boolean isOk, String msgAck, String user, String service, String component, long eventDate, String stremingEvent) {
        this.isOk = isOk;
        this.msgAck = msgAck;
        this.user = user;
        this.service = service;
        this.component = component;
        this.eventDate = eventDate;
        this.stremingEvent = stremingEvent;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public String getMsgAck() {
        return msgAck;
    }

    public void setMsgAck(String msgAck) {
        this.msgAck = msgAck;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public long getEventDate() {
        return eventDate;
    }

    public void setEventDate(long eventDate) {
        this.eventDate = eventDate;
    }

    public String getStremingEvent() {
        return stremingEvent;
    }

    public void setStremingEvent(String stremingEvent) {
        this.stremingEvent = stremingEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ack ack = (Ack) o;

        if (isOk != ack.isOk) return false;
        if (eventDate != ack.eventDate) return false;
        if (msgAck != null ? !msgAck.equals(ack.msgAck) : ack.msgAck != null) return false;
        if (user != null ? !user.equals(ack.user) : ack.user != null) return false;
        if (service != null ? !service.equals(ack.service) : ack.service != null) return false;
        if (component != null ? !component.equals(ack.component) : ack.component != null) return false;
        return stremingEvent != null ? stremingEvent.equals(ack.stremingEvent) : ack.stremingEvent == null;
    }

    @Override
    public int hashCode() {
        int result = (isOk ? 1 : 0);
        result = 31 * result + (msgAck != null ? msgAck.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (service != null ? service.hashCode() : 0);
        result = 31 * result + (component != null ? component.hashCode() : 0);
        result = 31 * result + (int) (eventDate ^ (eventDate >>> 32));
        result = 31 * result + (stremingEvent != null ? stremingEvent.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ack{" +
                "isOk=" + isOk +
                ", msgAck='" + msgAck + '\'' +
                ", user='" + user + '\'' +
                ", service='" + service + '\'' +
                ", component='" + component + '\'' +
                ", eventDate=" + eventDate +
                ", stremingEvent='" + stremingEvent + '\'' +
                '}';
    }
} /** End of class**/
