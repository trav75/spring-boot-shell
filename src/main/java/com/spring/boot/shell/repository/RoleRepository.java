package com.spring.boot.shell.repository;

import com.spring.boot.shell.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository  extends CrudRepository<Role, Long> {

    Role findByRoleName(String roleName);
}
