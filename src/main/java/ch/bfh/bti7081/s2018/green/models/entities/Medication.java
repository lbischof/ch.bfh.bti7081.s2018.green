package ch.bfh.bti7081.s2018.green.models.entities;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class Medication {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private LocalDateTime start;
    private LocalDateTime stop;
    private int periode;
    private float dose;

    @OneToOne
    @JoinColumn(name = "prescriberId", nullable = false)
    private Staff prescriber;
    
    @OneToOne
    @JoinColumn(name = "patientId", nullable = false)
    private Patient patient;
    
    @Column(nullable = false)
    private LocalDateTime updated;

    @Column(nullable = false, updatable = false)
    private LocalDateTime created;

    public Medication () {
        // required by JPA
    }

    public Medication (String name, LocalDateTime start, LocalDateTime end, int periode, float dose, Staff prescriber, Patient patient) {
        this.name = name;
        this.start = start;
        this.stop = end;
        this.periode = periode;
        this.dose = dose;
        this.prescriber = prescriber;
        this.patient = patient;
    }

    @PrePersist
    public void onPrePersist() {
        created = LocalDateTime.now();
        updated = LocalDateTime.now();
    }
      
    @PreUpdate
    public void onPreUpdate() {
    	updated = LocalDateTime.now();
    }

    public boolean isActive() {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(start) && now.isBefore(stop);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return start;
    }

    public void setStartDate(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEndDate() {
        return stop;
    }

    public void setEndDate(LocalDateTime end) {
        this.stop = end;
    }

    public int getPeriode() {
        return periode;
    }

    public void setPeriode(int periode) {
        this.periode = periode;
    }

    public float getDose() {
        return dose;
    }

    public void setdose(float dose) {
        this.dose = dose;
    }

    public Staff getPrescriber() {
        return prescriber;
    }

    public void setPrescriber(Staff prescriber) {
        this.prescriber = prescriber;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Patient getPatient () { return patient; }
}