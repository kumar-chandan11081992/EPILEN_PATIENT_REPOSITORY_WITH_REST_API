package com.td.chandan.dao;

import java.security.Key;
import java.util.Iterator;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.td.chandan.model.Patient;

/**
 * @author Kumar Chandan
 *
 */
@Repository("patientDao")
public class PatientDaoImpl implements PatientDao {

	private static final String ALGO = "AES";
    private static final byte[] keyValue = 
    new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't','S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addPatient(Patient patient) {
		String disease=patient.getPatientDisease();
		try {
			patient.setPatientDisease(PatientDaoImpl.encrypt(disease));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sessionFactory.getCurrentSession().saveOrUpdate(patient);
	}

	@SuppressWarnings("unchecked")
	public List<Patient> listPatients() {
		return (List<Patient>) sessionFactory.getCurrentSession().createCriteria(Patient.class).list();
	}

	public Patient getPatient(int patientid) {
		//return (Patient) sessionFactory.getCurrentSession().get(Patient.class, patientid);
		//return  sessionFactory.getCurrentSession().createCriteria(Patient.class).add(Restrictions.eq("patientid", 1));
		//return (Patient) sessionFactory.getCurrentSession().createQuery("SELECT * FROM Patient WHERE patientid = "+patientid);
		//Query q1= sessionFactory.getCurrentSession().createQuery("SELECT FROM Patient WHERE patientid = "+patientid);
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(Patient.class);
		cr.add(Restrictions.eq("patientId", patientid));
		List patients = cr.list();
		Patient p1=new Patient();
		
		 for (Iterator iterator = patients.iterator(); iterator.hasNext();)
		 {
			 p1 = (Patient) iterator.next(); 
			 break;
		 }
		
		return p1;
	}

	public void deletePatient(Patient patient) {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM Patient WHERE patientid = "+patient.getPatientId()).executeUpdate();
	}

	public void selectPatient(Patient patient)
	{
		
		sessionFactory.getCurrentSession().createQuery("SELECT FROM Patient WHERE patientid = "+patient.getPatientId()).executeUpdate();
		
	}
	
public static String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
}
}
