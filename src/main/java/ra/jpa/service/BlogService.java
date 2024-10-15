package ra.jpa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.jpa.entity.Blog;
import ra.jpa.repository.BlogRepo;

import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    private BlogRepo blogRepository;

    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    public Optional<Blog> findById(int id) {
        return blogRepository.findById(id);
    }

    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    public void deleteById(int id) {
        blogRepository.deleteById(id);
    }

}
