package com.hms_fe.model;

import java.util.List;

public class AppointmentForm {

    private List<Doctor> doctors;
    private List<Patient> patients;

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

}
