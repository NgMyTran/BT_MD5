package ra.session2_hl_jpa.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ra.session2_hl_jpa.model.entity.Employees;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employees,Integer> {
//2. Tìm các nhân viên có tổng giá trị hợp đồng phụ trách trên 500,000
    @Query("select e from Employees e join e.contracts c GROUP BY e HAVING SUM(c.contractValue)>500000")
    List<Employees> findEmployeesWithContractValueOver500000();
}
