package ra.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.jpa.entity.Blog;

@Repository
public interface BlogRepo extends JpaRepository<Blog, Integer> {
}
