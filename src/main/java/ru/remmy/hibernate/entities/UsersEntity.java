package ru.remmy.hibernate.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "remmydb", catalog = "")
public class UsersEntity {

    private int id;
    private String phone_number;
    private String name;
    private String initials;

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
    @Column(name = "phone_number", nullable = true, length = 64)
    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 128)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "initials", nullable = true, length = 45)
    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(initials, that.initials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, initials);
    }
}
