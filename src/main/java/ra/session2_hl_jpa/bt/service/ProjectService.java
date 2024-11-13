package ra.session2_hl_jpa.bt.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.session2_hl_jpa.bt.model.Project;
import ra.session2_hl_jpa.bt.repository.ProjectRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepository;

    public Set<Project> getProjectsByIds(Set<Integer> ids) {
        return new HashSet<>(projectRepository.findAllById(ids));
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Integer id) {
        return projectRepository.findById(id).orElse(null);
    }

    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }
}
