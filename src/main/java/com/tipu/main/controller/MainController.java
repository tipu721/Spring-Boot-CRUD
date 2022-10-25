package com.tipu.main.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tipu.main.model.Employee;
import com.tipu.main.repository.EmployeeRepo;

@Controller
public class MainController {

	@Autowired

	EmployeeRepo er;

	@GetMapping("/")
	public String homePage() {

		return "redirect:view";
	}

	@GetMapping("/addEmployee")
	public String addEmployee() {

		return "AddEmployee.html";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute Employee employee) {

		er.save(employee);
		return "redirect:/view";
	}
	
	
	@GetMapping("/view")
	public String viewEmployee(Model model) {
		
		
		model.addAttribute("employees", er.findAll());
		return "Employee.html";
	}
	
	@GetMapping("/edit/{eId}")
	public String editEmployee(@PathVariable("eId") int eId, Model model) {
		
		Employee employee = er.findById(eId).orElseThrow();
		model.addAttribute("employee", employee);
		return "UpdateEmployee.html";
		
	}
	
	@PostMapping("edit/update/{eId}")
	public String updateEmployee(@PathVariable("eId") int eId, Employee employee, BindingResult result, Model model) {

		er.save(employee);
		return "redirect:/view";
		
	}
	
	@GetMapping("/delete/{eId}")
	public String deleteEmployee(@PathVariable("eId") int eId, Model model) {
	    Employee employee = er.findById(eId)
	      .orElseThrow();
	    er.delete(employee);
	    return "redirect:/view";
	}


}
