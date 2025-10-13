package sk.ukf.uloha2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.ukf.uloha2.entity.Employee;
import sk.ukf.uloha2.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            throw new RuntimeException("Employee with id " + id + " not found");
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        employee.setId(0);
        return employeeService.save(employee);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.findById(id);
        if (updatedEmployee == null) {
            throw new RuntimeException("Employee with id " + id + " not found");
        }
        employee.setId(id);
        return employeeService.save(employee);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            throw new RuntimeException("Employee with id " + id + " not found");
        }
        employeeService.deleteById(id);
        return "Deleted student id -" + id;
    }
}
