package com.assignment.Assignment.Models;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EmployeeRepository extends MongoRepository<Employee, ObjectId> {

    public Employee findById(String id);

    public void deleteById(String id);

    Page<Employee> findAllByOrderByEmployeeNameAsc(Pageable pageable);

    Page<Employee> findAllByOrderByPhoneNumberAsc(Pageable pageable);

    Page<Employee> findAllByOrderByEmailAsc(Pageable pageable);

}
