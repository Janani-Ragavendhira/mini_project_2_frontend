package com.hms_fe.dto;

import java.util.Date;

public class AppointmentDto {

    private Long id;
    private String doctorName;
    private String patientName;
    private String patientMobile;
    private String appointmentReason;
    private Date appointmentAt;

    public AppointmentDto(Long id, String doctorName, String patientName, String patientMobile, Date appointmentAt, String appointmentReason) {
        this.id = id;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.patientMobile = patientMobile;
        this.appointmentAt = appointmentAt;
        this.appointmentReason = appointmentReason;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientMobile() {
        return patientMobile;
    }

    public void setPatientMobile(String patientMobile) {
        this.patientMobile = patientMobile;
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
}