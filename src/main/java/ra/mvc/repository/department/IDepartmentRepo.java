package ra.mvc.repository.department;

import org.springframework.data.repository.CrudRepository;
import ra.mvc.model.Department;
import ra.mvc.model.Employee;
import ra.mvc.repository.IRepository;

import java.util.List;

public interface IDepartmentRepo extends IRepository<Department, Integer> {
}
