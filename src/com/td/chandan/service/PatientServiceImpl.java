package com.td.chandan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.td.chandan.dao.PatientDao;
import com.td.chandan.model.Patient;

/**
 * @author Kumar Chandan
 *
 */
@Service("patientService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientDao patientDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addPatient(Patient patient) {
		patientDao.addPatient(patient);
	}
	
	public List<Patient> listPatients() {
		return patientDao.listPatients();
	}

	public Patient getPatient(int patientid) {
		return patientDao.getPatient(patientid);
	}
	
	public void deletePatient(Patient patient) {
		patientDao.deletePatient(patient);
	}

	public void selectPatient(Patient patient)
	{
		patientDao.selectPatient(patient);
	}
}
