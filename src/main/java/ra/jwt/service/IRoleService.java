package ra.jwt.service;

import ra.jwt.entity.RoleName;
import ra.jwt.entity.Roles;

public interface IRoleService {
    Roles findByRoleName(RoleName roleName);
}
