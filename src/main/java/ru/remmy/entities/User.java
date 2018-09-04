package ru.remmy.entities;

import org.codehaus.jackson.map.annotate.JsonRootName;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Objects;
@XmlRootElement(name = "user")
public class User {
    private String initials;
    public User(String name) {
        this.name = name;
    }

    public User() {
    }

    public String getInitials() {
        String[] _initials = this.name.split(" ");
        String result = _initials[0].substring(0,1)+_initials[1].substring(0,1);
        return result;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
}

