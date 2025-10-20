package sk.ukf.uloha2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.ukf.uloha2.dao.EmployeeDAO;
import sk.ukf.uloha2.dao.EmployeeRepository;
import sk.ukf.uloha2.entity.Employee;
import sk.ukf.uloha2.exception.EmailAlreadyExistsException;
import sk.ukf.uloha2.exception.ObjectNotFoundException;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    /*
    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
    */


    /*@Override
    public List<Employee> findAll(){
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int id){
        return employeeDAO.findById(id);
    }

    @Transactional
    @Override
    public Employee save(Employee employee){
        return employeeDAO.save(employee);
    }

    @Transactional
    @Override
    public void deleteById(int id){
        employeeDAO.deleteById(id);
    }
     */
    @Override
    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        return this.employeeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Employee",id));
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {
        if (employee.getId() == 0) {
            if(employeeRepository.existsByEmail(employee.getEmail())) {
                throw new EmailAlreadyExistsException(employee.getEmail());
            }
        } else{
            Employee existingWithEmail = employeeRepository.findByEmail(employee.getEmail()).orElse(null);

            if (existingWithEmail != null && existingWithEmail.getId() != employee.getId()) {
                throw new EmailAlreadyExistsException(employee.getEmail());
            }
        }

        return this.employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        if(!employeeRepository.existsById(id)) {
            throw new ObjectNotFoundException("Employee",id);
        }
        this.employeeRepository.deleteById(id);
    }
}
