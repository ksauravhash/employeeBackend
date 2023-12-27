package com.assignment.Assignment.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import com.assignment.Assignment.Models.EmployeeRepository;

import jakarta.mail.MessagingException;

import com.assignment.Assignment.DTO.*;
import com.assignment.Assignment.Models.Employee;;

@Service
public class EmployeeService {

    final short pageSize = 20;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmailService emailService;

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

        String subject = "New Employee";
        String body = "<html><body>" + emp.getEmployeeName() + " will now work under you. Mobile number is "
                + emp.getPhoneNumber()
                + " and email is <a href=\"mailto:" + emp.getEmail() + "\">" + emp.getEmail() + "</a></body></html>";
        try {
            emailService.sendEmail(emp.getReportsTo().getEmail(), subject, body);
        } catch (MessagingException e) {
            System.out.println("Some error occured");
        }

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

    public Employee getNthLevelManager(GetNManager g) {
        int level = 0;
        System.out.println(g);
        Employee emp = employeeRepository.findById(g.getId());
        System.out.println(emp);
        while (level < g.getN()) {
            if (emp == null)
                break;
            emp = emp.getReportsTo();
            level++;
        }
        return emp;
    }

    public Page<Employee> getEmployeesWithPagnationAndSorting(GetEmployeesWithPaginationAndSorting ge) {

        if (ge.getSortBy().equalsIgnoreCase("employeename")) {
            Pageable pageable = PageRequest.of(ge.getPageNo(), pageSize);
            return employeeRepository.findAllByOrderByEmployeeNameAsc(pageable);
        } else if (ge.getSortBy().equalsIgnoreCase("phonenumber")) {
            Pageable pageable = PageRequest.of(ge.getPageNo(), pageSize);
            return employeeRepository.findAllByOrderByPhoneNumberAsc(pageable);
        } else if (ge.getSortBy().equalsIgnoreCase("email")) {
            Pageable pageable = PageRequest.of(ge.getPageNo(), pageSize);
            return employeeRepository.findAllByOrderByEmailAsc(pageable);
        } else {
            Pageable pageable = PageRequest.of(ge.getPageNo(), pageSize);
            return employeeRepository.findAll(pageable);
        }

    }
}
