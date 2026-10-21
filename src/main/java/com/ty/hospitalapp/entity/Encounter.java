package com.ty.hospitalapp.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Encounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eid;

    private String reason;

    private String date;

    private String discharge;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @JsonIgnore
    @OneToMany(mappedBy = "encounter", cascade = CascadeType.ALL)
    private List<Observation> observations;

    @JsonIgnore
    @OneToMany(mappedBy = "encounter", cascade = CascadeType.ALL)
    private List<MedOrder> medOrders;

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDischarge() {
        return discharge;
    }

    public void setDischarge(String discharge) {
        this.discharge = discharge;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<Observation> getObservations() {
        return observations;
    }

    public void setObservations(List<Observation> observations) {
        this.observations = observations;
    }

    public List<MedOrder> getMedOrders() {
        return medOrders;
    }

    public void setMedOrders(List<MedOrder> medOrders) {
        this.medOrders = medOrders;
    }

}
