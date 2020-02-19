package dev.devzero.api.repository;

import org.springframework.stereotype.Repository;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.model.UserPrincipal;

@Repository
public interface UserPrincipalRepository extends BaseRepository<UserPrincipal, Long> {

	public UserPrincipal findByUsername(String username);

	public UserPrincipal findByEmailIgnoreCase(String email);

	public Boolean existsByUsername(String username);

	public Boolean existsByEmail(String email);

}
