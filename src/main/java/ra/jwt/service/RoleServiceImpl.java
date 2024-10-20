package ra.jwt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.jwt.entity.RoleName;
import ra.jwt.entity.Roles;
import ra.jwt.repository.IRoleRepository;

import javax.management.relation.RoleNotFoundException;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {
    private final IRoleRepository roleRepository;

    @Override
    public Roles findByRoleName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName).orElseThrow(() -> new RuntimeException("role not found"));
    }
}
