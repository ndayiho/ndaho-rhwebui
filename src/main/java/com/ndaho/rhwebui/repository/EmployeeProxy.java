package com.ndaho.rhwebui.repository;

import com.ndaho.rhwebui.configuration.CustomProperties;
import com.ndaho.rhwebui.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class EmployeeProxy {

    @Autowired
    private CustomProperties props;

    /**
     * Get all employees
     *
     * @return An iterable of all employees
     */

    public Iterable<Employee> getEmployees() {
        String baseApiUrl = props.getApiUrl();
        String getEmployeesUrl = baseApiUrl + "/employees";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(
                getEmployeesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        log.debug("Get Employees call " + response.getStatusCode());

        return response.getBody();
    }

    public Employee createEmployee(Employee e) {
        String baseApiUrl = props.getApiUrl();
        String createEmployeeUrl = baseApiUrl + "/employee";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> request = new HttpEntity<>(e);
        ResponseEntity<Employee> response = restTemplate.exchange(
                createEmployeeUrl,
                HttpMethod.POST,
                request,
                Employee.class);

        log.debug("Create Employee call " + response.getStatusCode());

        return response.getBody();
    }


    public Employee getEmployee(int id) {
        String baseApiUrl = props.getApiUrl();
        String getEmployeesUrl = baseApiUrl + "/employee/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> response = restTemplate.exchange(
                getEmployeesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        log.debug("Get Employees call " + response.getStatusCode());

        return response.getBody();
    }

    public void deleteEmployee(int id) {
        String baseApiUrl = props.getApiUrl();
        String createEmployeeUrl = baseApiUrl + "/employee/" + id;
        Employee employee = getEmployee(id);
        if (employee != null) {
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Employee> request = new HttpEntity<>(employee);
            ResponseEntity<Employee> response = restTemplate.exchange(
                    createEmployeeUrl,
                    HttpMethod.DELETE,
                    request,
                    Employee.class);

            log.debug("delete Employee call " + response.getStatusCode());
        }

    }

    public Employee updateEmployee(Employee employee) {
        throw new UnsupportedOperationException();
    }
}
