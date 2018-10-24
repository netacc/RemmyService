package ru.remmy.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "group", schema = "test", catalog = "")
public class GroupEntity {
    private int idgroup;

    @Id
    @Column(name = "idgroup", nullable = false)
    public int getIdgroup() {
        return idgroup;
    }

    public void setIdgroup(int idgroup) {
        this.idgroup = idgroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupEntity that = (GroupEntity) o;
        return idgroup == that.idgroup;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idgroup);
    }
}
