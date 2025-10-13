package sk.ukf.uloha2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sk.ukf.uloha2.entity.Employee;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDAO{
    private EntityManager entityManager;

    @Autowired
    public EmployeeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query =
                entityManager.createQuery("from Employee", Employee.class);
        List<Employee> employees = query.getResultList();
        return employees;
    }

    @Override
    public Employee findById(int id){
        return entityManager.find(Employee.class, id);
    }

    @Override
    public Employee save(Employee employee){
        return entityManager.merge(employee);
    }

    @Override
    public void deleteById(int id){
        entityManager.remove(findById(id));
    }
}
