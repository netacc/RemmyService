package ru.remmy.hibernate.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "tasks", schema = "test")
@XmlRootElement(name="task")
public class Task {
    private int id;
    private long doerId;
    private String doerName;
    private String header;
    private String text;
    private Date taskDate;
    private String doerInitials;
    private int status;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "doerId", nullable = false)
    public long getDoerId() {
        return doerId;
    }

    public void setDoerId(long doerId) {
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
    @Column(name = "header", nullable = true, length = 64)
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Basic
    @Column(name = "text", nullable = true, length = 256)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "taskDate", nullable = true)
    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
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
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(doerId, task.doerId) &&
                Objects.equals(doerName, task.doerName) &&
                Objects.equals(header, task.header) &&
                Objects.equals(text, task.text) &&
                Objects.equals(taskDate, task.taskDate) &&
                Objects.equals(doerInitials, task.doerInitials) &&
                Objects.equals(status, task.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doerId, doerName, header, text, taskDate, doerInitials, status);
    }
}
