package ra.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.jwt.entity.RoleName;
import ra.jwt.entity.Roles;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByRoleName(RoleName roleName);
}
