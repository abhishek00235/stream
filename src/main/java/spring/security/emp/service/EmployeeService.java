package spring.security.emp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.security.emp.Entity.EmployeeEntity;
import spring.security.emp.repository.EmployeeRepository;

import java.util.*;
import java.util.stream.Collectors;

/*Question 1: Find the average salary of employees in each department,
but only consider departments with more than 3 employees.
 Sort the results by department name in ascending order.

Question 2: Find the top 3 employees with the highest salary in each department.
 If a department has less than 3 employees, return all employees in that department.
 Return a map where the key is the department name and the value is a list of employees.

Question 3: Find all employees who have a salary greater than the average salary of their
department. Return a list of these employees, sorted by their salary in descending order.

Question 4: Find the department with the highest average salary. If there are multiple
departments with the same highest average salary, return all of them.
 Return a list of department names.

Question 5: Find all employees who have a salary within 10% of the maximum salary in their
 department. Return a list of these employees, sorted by their department name and then
  by their salary in ascending order.

Question 6: Find the number of employees in each department who have a salary greater
than the median salary of their department. Return a map where the key is the department
 name and the value is the count of employees.

Question 7: Find all employees who are in the top 25% of earners in their department.
 Return a list of these employees, sorted by their department name and then by
 their salary in descending order.

Question 8: Find the department with the lowest average salary, but only consider
 departments with more than 5 employees. If there are multiple departments with the
 same lowest average salary, return all of them. Return a list of department names.
 */

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeEntity> getEmployees() {
        List<EmployeeEntity> employees = employeeRepository.findAll();

        System.out.println("max salary: " + employees.stream()
                .max(Comparator.comparingLong(EmployeeEntity::getSalary))
                .get().getSalary());

        System.out.println("sort by salary " + employees.stream()
                .sorted(Comparator.comparing(EmployeeEntity::getSalary)).
                toList());

        System.out.println("sec. max salary" + employees.stream()
                .sorted(Comparator.comparingLong(EmployeeEntity::getSalary))
                .toList().get(employees.size() - 2).getSalary());

     /*   Map<String, Double> groupSalary = employees.stream()
                .collect(Collectors.groupingBy(EmployeeEntity::getDepartment
                        ,Collectors.averagingDouble(EmployeeEntity::getSalary)
                        ,Collectors.filtering(e->employees.stream().filter(d->d.getDepartment()))));*/

        System.out.println(employees.stream()
                .sorted(Comparator
                        .comparing(EmployeeEntity::getSalary)
                        .reversed()).collect(Collectors.toList()));


        employees.sort(Comparator.comparingDouble(EmployeeEntity::getSalary).reversed());

        System.out.println("avg:"+employees.stream().collect(Collectors.summarizingLong(EmployeeEntity::getSalary)).getAverage());

        System.out.println(employees
                .stream()
                .collect(Collectors
                        .partitioningBy(data
                                -> data.getSalary()>10000)));
    return null;
    }

}
