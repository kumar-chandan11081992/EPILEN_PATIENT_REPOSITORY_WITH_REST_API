package com.td.chandan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.td.chandan.bean.PatientBean;
import com.td.chandan.dao.PatientDaoImpl;
import com.td.chandan.model.Patient;
import com.td.chandan.service.PatientService;

/**
 * @author Kumar Chandan
 *
 */
@Controller
public class PatientController {
	
	@Autowired
	private PatientService patientService;
		
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView savePatient(@ModelAttribute("command") PatientBean patientBean, 
			BindingResult result) {
		Patient patient = prepareModel(patientBean);
		patientService.addPatient(patient);
		return new ModelAndView("redirect:/add.html");
	}

	@RequestMapping(value="/patients", method = RequestMethod.GET)
	public ModelAndView listPatients() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("patients",  prepareListofBean(patientService.listPatients()));
		return new ModelAndView("patientsList", model);
	}
	@RequestMapping(value="/search_res", method = RequestMethod.GET)
	public ModelAndView searchPatient(@RequestParam("patientid") int id) {
		
		Map<String, Object> model = new HashMap<String, Object>();
	    model.put("patients",  preparePatientBean(patientService.getPatient(id)));
	    
	    //PatientBean p1=(PatientBean)model.get("patients");
	    //System.out.println(p1.getDisease());
		return new ModelAndView("patientSearch",model);
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public ModelAndView selectPatient() {
		/*Map<String, Object> model = new HashMap<String, Object>();
	    model.put("patients",  preparePatientBean(patientService.getPatient(1)));*/
		ModelAndView mav=new ModelAndView();
		mav.setViewName("patientSearch");
		return mav;
	 //   return new ModelAndView("patientSearch", model);
	}
 
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addPatient(@ModelAttribute("command")  PatientBean patientBean,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("patients",  prepareListofBean(patientService.listPatients()));
		return new ModelAndView("addPatient", model);
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView editPatient(@ModelAttribute("command")  PatientBean patientBean,
			BindingResult result) {
		patientService.deletePatient(prepareModel(patientBean));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("patient", null);
		model.put("patients",  prepareListofBean(patientService.listPatients()));
		return new ModelAndView("addPatient", model);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView deletePatient(@ModelAttribute("command")  PatientBean patientBean,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		Patient p_decrypt=new Patient();
		p_decrypt=patientService.getPatient(patientBean.getId());
		try {
			p_decrypt.setPatientDisease(PatientDaoImpl.decrypt(p_decrypt.getPatientDisease()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.put("patient", preparePatientBean(p_decrypt));
		model.put("patients",  prepareListofBean(patientService.listPatients()));
		//model.put("patient", preparePatientBean(patientService.getPatient(patientBean.getId())));
		return new ModelAndView("addPatient", model);
	}
	
	private Patient prepareModel(PatientBean patientBean){
		Patient patient = new Patient();
		patient.setPatientAddress(patientBean.getAddress());
		patient.setPatientAge(patientBean.getAge());
		patient.setPatientName(patientBean.getName());
		patient.setPatientDisease(patientBean.getDisease());
		patient.setPatientId(patientBean.getId());
		patientBean.setId(null);
		return patient;
	}
	
	private List<PatientBean> prepareListofBean(List<Patient> patients){
		List<PatientBean> beans = null;
		if(patients != null && !patients.isEmpty()){
			beans = new ArrayList<PatientBean>();
			PatientBean bean = null;
			for(Patient patient : patients){
				bean = new PatientBean();
				bean.setName(patient.getPatientName());
				bean.setId(patient.getPatientId());
				bean.setAddress(patient.getPatientAddress());
				bean.setDisease(patient.getPatientDisease());
				bean.setAge(patient.getPatientAge());
				beans.add(bean);
			}
		}
		return beans;
	}
	
	
	private PatientBean preparePatientBean(Patient patient){
		PatientBean bean = new PatientBean();
		bean.setAddress(patient.getPatientAddress());
		bean.setAge(patient.getPatientAge());
		bean.setName(patient.getPatientName());
		bean.setDisease(patient.getPatientDisease());
		bean.setId(patient.getPatientId());
		return bean;
	}
}
