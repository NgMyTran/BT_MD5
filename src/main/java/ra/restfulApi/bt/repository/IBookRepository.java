package ra.restfulApi.bt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import projectmd5.bt.model.Book;

public interface IBookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByNameContainingOrCategoryNameContaining(String name, String categoryName, Pageable pageable);
}
