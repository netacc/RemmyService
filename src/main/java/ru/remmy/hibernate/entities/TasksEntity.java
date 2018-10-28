package ru.remmy.hibernate.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tasks", schema = "remmydb", catalog = "")
public class TasksEntity {
    private int id;
    private int doerId;
    private String doerName;
    private String header;
    private String text;
    private String taskDate;
    private String doerInitials;
    private int status;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "doerId", nullable = false)
    public int getDoerId() {
        return doerId;
    }

    public void setDoerId(int doerId) {
        this.doerId = doerId;
    }

    @Basic
    @Column(name = "doerName", nullable = true, length = 64)
    public String getDoerName() {
        return doerName;
    }

    public void setDoerName(String doerName) {
        this.doerName = doerName;
    }

    @Basic
    @Column(name = "header", nullable = true, length = 256)
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Basic
    @Column(name = "text", nullable = true, length = 64)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "taskDate", nullable = true, length = 45)
    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    @Basic
    @Column(name = "doerInitials", nullable = true, length = 64)
    public String getDoerInitials() {
        return doerInitials;
    }

    public void setDoerInitials(String doerInitials) {
        this.doerInitials = doerInitials;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TasksEntity that = (TasksEntity) o;
        return id == that.id &&
                doerId == that.doerId &&
                Objects.equals(doerName, that.doerName) &&
                Objects.equals(header, that.header) &&
                Objects.equals(text, that.text) &&
                Objects.equals(taskDate, that.taskDate) &&
                Objects.equals(doerInitials, that.doerInitials) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doerId, doerName, header, text, taskDate, doerInitials, status);
    }
}
