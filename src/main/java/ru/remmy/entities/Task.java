package ru.remmy.entities;

import org.codehaus.jackson.map.annotate.JsonRootName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@XmlRootElement(name="task")
public class Task {
    private int id;
    private int doerId;
    private String doerName;
    private String header;
    private String text;
    private String taskDate;
    private String doerInitials;
    private int status;

    public String getDoerName() {
        return doerName;
    }

    public void setDoerName(String doerName) {
        this.doerName = doerName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDoerInitials() {
        return doerInitials;
    }

    public void setDoerInitials(String doerInitials) {
        String[] _initials = doerInitials.split(" ");
        //TODO: Проверить наличие ИО
        this.doerInitials = _initials[0].substring(0,1)+_initials[1].substring(0,1);
    }

    public Task() {
    }

    public Task(int doerId, String header, String text, String taskDate) {
        this.doerId = doerId;
        this.header = header;
        this.text = text;
        this.taskDate = taskDate;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoerId() {
        return doerId;
    }

    public void setDoerId(int doerId) {
        this.doerId = doerId;
    }

    public String getHeader() {
        if (header.isEmpty()){

            if(text.length()>=15){
                header = text.substring(0,15);
                if (text.length()>15) header = header+"...";
            }else header = text;
        }
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) throws ParseException {
        Date d =null;
        SimpleDateFormat f2 = null;
        try {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
            d = f.parse(taskDate); // Format String to a dateobject with the format provided by the String.
            f2 = new SimpleDateFormat("dd.MM.yyyy HH:mm"); // MMMM for full month name
            this.taskDate = f2.format(d);
        } catch(Exception e) {
            try{
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                d = f.parse(taskDate); // Format String to a dateobject with the format provided by the String.
                f2 = new SimpleDateFormat("dd.MM.yyyy HH:mm"); // MMMM for full month name
                this.taskDate = f2.format(d);
            } catch (Exception e1) {
                this.taskDate = taskDate;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (doerId != task.doerId) return false;
        if (header != null ? !header.equals(task.header) : task.header != null) return false;
        if (text != null ? !text.equals(task.text) : task.text != null) return false;
        return taskDate != null ? taskDate.equals(task.taskDate) : task.taskDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + doerId;
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (taskDate != null ? taskDate.hashCode() : 0);
        return result;
    }
}

