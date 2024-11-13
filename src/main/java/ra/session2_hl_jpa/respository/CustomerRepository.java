package ra.session2_hl_jpa.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ra.session2_hl_jpa.model.entity.Customers;

import java.util.List;
import java.util.Objects;

public interface CustomerRepository extends JpaRepository<Customers,Integer> {
//1. Tìm tất cả các khách hàng có số lượng hợp đồng trên 5
    @Query("select c from Customers c where size(c.contracts)>5")
    List<Customers> findCustomersWithMoreThanFiveContracts();

    //5.Lấy danh sách các khách hàng và số lượng các tương tác với mỗi khách hàng trong tháng hiện tại
    @Query("SELECT c, COUNT(i) FROM Customers c " +
            "LEFT JOIN c.interactions i " +
            "WHERE FUNCTION('YEAR', i.interactionDate) = :year " +
            "AND FUNCTION('MONTH', i.interactionDate) = :month " +
            "GROUP BY c")
    List<Object[]> findCustomersInteractionCountForCurrentMonth(@Param("year") int year, @Param("month") int month);
}
