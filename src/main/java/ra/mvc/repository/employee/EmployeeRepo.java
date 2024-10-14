package ra.mvc.repository.employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import ra.mvc.model.Employee;

import java.util.List;


@Component
public class EmployeeRepo implements IEmployeeRepo {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery("from Employee ", Employee.class);
        return query.getResultList();
    }

    @Override
    public Employee findById(Integer id) {
        TypedQuery<Employee> query = entityManager.createQuery("from Employee where id= :id", Employee.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    public void save(Employee employee) {
        if (employee.getId()==null){
            entityManager.persist(employee);
        }else {
            Employee cus = findById(employee.getId());
            cus.setFullName(employee.getFullName());
            cus.setStatus(employee.isStatus());
            entityManager.merge(cus);
        }
    }

    @Override
    public void deleteById(Integer id) {
        Employee employeeDel = findById(id);
        employeeDel.setStatus(false);
        entityManager.merge(employeeDel);
    }

    @Override
    public List<Employee> findByDepartmentId(Integer departmentId) {
        TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.department.id = :departmentId", Employee.class);
        query.setParameter("departmentId", departmentId);
        return query.getResultList();
    }
}
