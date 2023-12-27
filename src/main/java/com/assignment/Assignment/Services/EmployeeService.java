package com.assignment.Assignment.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.assignment.Assignment.Models.EmployeeRepository;
import com.assignment.Assignment.DTO.*;
import com.assignment.Assignment.Models.Employee;;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public String addEmployee(AddEmployee em) {
        Employee emp = new Employee();
        emp.setEmployeeName(em.getEmployeeName());
        emp.setEmail(em.getEmail());
        emp.setPhoneNumber(em.getPhoneNumber());
        emp.setProfileImage(em.getProfileImage());

        List<Employee> employees = employeeRepository.findAll();

        if (!employees.isEmpty()) {
            emp.setReportsTo(employees.get(0));
        }

        employeeRepository.insert(emp);
        return emp.getId();
    }

    public void deleteEmployee(DeleteEmployee em) {
        Employee e = employeeRepository.findById(em.getId());
        if (e != null)
            employeeRepository.deleteById(em.getId());

    }

    public void updateEmployee(UpdateEmployee em) {
        Employee emp = employeeRepository.findById(em.getId());
        if (em.getEmployeeName() != null && !em.getEmployeeName().equals(""))
            emp.setEmployeeName(em.getEmployeeName());
        if (em.getEmail() != null && !em.getEmployeeName().equals(""))
            emp.setEmail(em.getEmail());
        if (em.getPhoneNumber() != null && !em.getPhoneNumber().equals(""))
            emp.setPhoneNumber(em.getPhoneNumber());
        if (em.getProfileImage() != null && !em.getProfileImage().equals(""))
            emp.setProfileImage(em.getProfileImage());

        employeeRepository.save(emp);
    }
}
