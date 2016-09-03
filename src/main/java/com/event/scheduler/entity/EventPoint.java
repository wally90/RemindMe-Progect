package com.event.scheduler.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "event_points")
public class EventPoint {
    @Column(name = "id")
    private Integer id;
    @Column(name = "event_point_name")
    private String eventPointName;

    public EventPoint() {
    }

    public EventPoint(Integer id, String eventPointName) {
        this.id = id;
        this.eventPointName = eventPointName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventPointName() {
        return eventPointName;
    }

    public void setEventPointName(String eventPointName) {
        this.eventPointName = eventPointName;
    }

    @Override
    public String toString() {
        return "EventPoint{" +
                "id=" + id +
                ", eventPointName='" + eventPointName + '\'' +
                '}';
    }
}
