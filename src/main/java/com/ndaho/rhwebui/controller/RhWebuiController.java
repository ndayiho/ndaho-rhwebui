package com.ndaho.rhwebui.controller;

import com.ndaho.rhwebui.model.Employee;
import com.ndaho.rhwebui.service.RhWebuiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class RhWebuiController {
    @Autowired
    private RhWebuiService rhWebuiService;

    @GetMapping("/")
    private String displayHome(Model model) {
        Iterable<Employee> listEmployee = rhWebuiService.getEmployees();
        model.addAttribute("employees", listEmployee);
        return "rh-home";
    }

    @PostMapping("/saveEmployee")
    public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
        rhWebuiService.saveEmployee(employee);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/deleteEmployee/{id}")
    public ModelAndView deleteEmployee(@PathVariable("id") final int id) {
        rhWebuiService.deleteEmployee(id);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/saveEmployee")
    private String displayEmployeeForm(@ModelAttribute Employee employee) {
        return "employeeForm";
    }
}
