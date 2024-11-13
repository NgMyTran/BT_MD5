package ra.session2_hl_jpa.bt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.session2_hl_jpa.bt.model.Blog;

@Repository
public interface BlogRepo extends JpaRepository<Blog, Integer> {
}
