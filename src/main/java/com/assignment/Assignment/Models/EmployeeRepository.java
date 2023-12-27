package com.assignment.Assignment.Models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, ObjectId> {

    public Employee findById(String id);

    public void deleteById(String id);

}
