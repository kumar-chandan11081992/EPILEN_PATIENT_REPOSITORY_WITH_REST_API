package com.td.chandan.service;

import java.util.List;

import com.td.chandan.model.Patient;

/**
 * @author Kumar Chandan
 *
 */
public interface PatientService {
	
	public void addPatient(Patient patient);

	public List<Patient> listPatients();
	
	public Patient getPatient(int patientid);
	
	public void deletePatient(Patient patient);
	
	public void selectPatient(Patient patient);
}
