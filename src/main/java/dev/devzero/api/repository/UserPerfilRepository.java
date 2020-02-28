package dev.devzero.api.repository;

import org.springframework.stereotype.Repository;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.model.UserPerfil;

@Repository
public interface UserPerfilRepository extends BaseRepository<UserPerfil, Long> {

}