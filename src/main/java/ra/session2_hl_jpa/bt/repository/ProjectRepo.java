package ra.session2_hl_jpa.bt.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ra.session2_hl_jpa.bt.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
