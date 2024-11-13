package ra.session2_hl_jpa.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ra.session2_hl_jpa.model.entity.Contracts;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contracts,Integer> {
//3. Lấy danh sách tất cả các hợp đồng và trạng thái của dự án liên quan
    @Query("SELECT c, p.status FROM Contracts c LEFT JOIN c.projects p")
    List<Contracts> findAllContractsByProjectStatus();

    //4. Tìm các hợp đồng có ít nhất một dự án đang ở trạng thái "ON"
    @Query("SELECT DISTINCT c FROM Contracts c JOIN c.projects p WHERE p.status = ra.session2_hl_jpa.model.entity.enums.ProjectStatus.ON_HOLD")
    List<Contracts> findContractsWithAtLeastOneOnProject();
}
