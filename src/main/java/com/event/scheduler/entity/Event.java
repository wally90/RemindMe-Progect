package com.event.scheduler.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "events")
public class Event {
    @Column(name = "id")
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "event_points_id")
    private Integer eventPointId;

    private String eventPoint;

    @Column(name = "description")
    private String description;
    @Column(name = "start_date")
    private Timestamp startDate;
    @Column(name = "end_date")
    private Timestamp endDate;

    private String start;
    private String end;

    public Event() {
    }

    public Event(Integer id, String username, Integer eventPointId, String eventPoint, String description, Timestamp startDate, Timestamp endDate, String start, String end) {
        this.id = id;
        this.username = username;
        this.eventPointId = eventPointId;
        this.eventPoint = eventPoint;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.start = start;
        this.end = end;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getEventPointId() {
        return eventPointId;
    }

    public void setEventPointId(Integer eventPointId) {
        this.eventPointId = eventPointId;
    }

    public String getEventPoint() {
        return eventPoint;
    }

    public void setEventPoint(String eventPoint) {
        this.eventPoint = eventPoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", eventPointId=" + eventPointId +
                ", eventPoint='" + eventPoint + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }
}
