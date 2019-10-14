package com.savitoh.multipledatasourcesdemo.cartorio.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "cartorio", schema = "schema_foo")
public class Cartorio implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = -1607779848623712769L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cartorio cartorio = (Cartorio) o;
        return getId().equals(cartorio.getId()) &&
                Objects.equals(getName(), cartorio.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Cartorio{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
