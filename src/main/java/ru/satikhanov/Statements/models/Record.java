package ru.satikhanov.Statements.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "records")
public class Record {
    @Id
    @GeneratedValue
    private int idrecord;

    @ManyToOne
    @JoinColumn(name = "iddiscipline")
    private Discipline iddiscipline;

    @ManyToOne
    @JoinColumn(name = "idgroup")
    private Group idgroup;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "iduser")
    private User iduser;

    @ManyToOne
    @JoinColumn(name = "idtype")
    private Type idtype;

    @Override
    public String toString() {
        return "Record{" +
                "idrecord=" + idrecord +
                '}';
    }
}
