package ra.mvc.repository.department;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import ra.mvc.model.Department;
import ra.mvc.model.Employee;
import ra.mvc.service.EmployeeService;

import java.util.List;

@Component
public class DepartmentRepo implements IDepartmentRepo {

    private final EmployeeService employeeService;
    @PersistenceContext
    private EntityManager entityManager;

    public DepartmentRepo(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public List<Department> findAll() {
        TypedQuery<Department> query = entityManager.createQuery("from Department ", Department.class);
        return query.getResultList();
    }

    @Override
    public Department findById(Integer id) {
        TypedQuery<Department> query = entityManager.createQuery("from Department where id= :id", Department.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    public void save(Department department) {
        if (department.getId()==null){
            entityManager.persist(department);
        }else {
            Department cus = findById(department.getId());
            cus.setName(department.getName());
            cus.setStatus(department.isStatus());
            entityManager.merge(cus);
        }
    }

    @Override
    public void deleteById(Integer id) {
       Department departmentDel = findById(id);
       departmentDel.setStatus(false);
       entityManager.merge(departmentDel);
    }
}
