package dev.devzero.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.model.Authorities;

@Repository
public interface AuthoritiesRepository extends BaseRepository<Authorities, Long> {

	@Query("SELECT authorities " + " FROM Authorities authorities " + "WHERE"
			+ "  (:usernameValue is null or :usernameValue = authorities.userPrincipal.username )")
	public Optional<Authorities> findByUsername(@Param("usernameValue") String usernameValue);

}
