package sk.ukf.uloha2.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.ukf.uloha2.dto.ApiResponse;
import sk.ukf.uloha2.entity.Employee;
import sk.ukf.uloha2.exception.ObjectNotFoundException;
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
    public ResponseEntity<ApiResponse<List<Employee>>> getEmployees() {
        List<Employee> employees = employeeService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(employees));
    }

    /*public List<Employee> getEmployees() {
        return employeeService.findAll();
    }*/

    @GetMapping("/employees/{id}")

    public ResponseEntity<ApiResponse<Employee>> getEmployee(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        if (employee == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("Zamestnanec s daným ID sa nenašiel"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(employee));
    }
    /*public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            throw new RuntimeException("Zamestnanec s  " + id + " ID nebol nájdený");
        }
        return employee;
    }*/

    @PostMapping("/employees")
    public ResponseEntity<ApiResponse<Employee>> addEmployee(@Valid @RequestBody Employee employee){
        employee.setId(0);
        Employee savedEmployee = employeeService.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(savedEmployee,"Zamestnanec bol úspešne vytvorený"));
    }

    /*public Employee addEmployee(@RequestBody Employee employee) {
        employee.setId(0);
        return employeeService.save(employee);
    }*/

    @PutMapping("/employees/{id}")
    public ResponseEntity<ApiResponse<Employee>> updateEmployee(
            @PathVariable int id, @Valid @RequestBody Employee employee
    ){
        employeeService.findById(id);
        employee.setId(id);
        Employee updatedEmployee = employeeService.save(employee);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(updatedEmployee,"Zamestnanec úspešne aktualizovaný"));
    }

    /*public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.findById(id);
        if (updatedEmployee == null) {
            throw new RuntimeException("Employee with id " + id + " not found");
        }
        employee.setId(id);
        return employeeService.save(employee);
    }*/

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<ApiResponse<String>> deleteEmployee(@PathVariable int id){
        Employee employee = employeeService.findById(id);
        if (employee == null){
            throw new ObjectNotFoundException("Zamestnanec",id);
        }
        employeeService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Zamestnanec sa úspešne odstránil"));
    }

    /*public String deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            throw new RuntimeException("Zamestnanec s " + id + " ID nebol nájdený");
        }
        employeeService.deleteById(id);
        return "Deleted student id -" + id;
    }*/
}
