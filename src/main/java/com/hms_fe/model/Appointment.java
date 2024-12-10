package com.hms_fe.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Appointment {

    private Long id;

    private Long doctorId;

    private Long patientId;

    private Long userId;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date appointmentAt;

    private String appointmentReason;

    private String prescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getAppointmentAt() {
        return appointmentAt;
    }

    public void setAppointmentAt(Date appointmentAt) {
        this.appointmentAt = appointmentAt;
    }

    public String getAppointmentReason() {
        return appointmentReason;
    }

    public void setAppointmentReason(String appointmentReason) {
        this.appointmentReason = appointmentReason;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }
}
