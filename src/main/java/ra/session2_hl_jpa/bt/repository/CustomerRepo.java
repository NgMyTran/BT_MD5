package ra.session2_hl_jpa.bt.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ra.session2_hl_jpa.bt.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
}

