package dev.devzero.api.repository;

import org.springframework.stereotype.Repository;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.model.Role;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {

}
