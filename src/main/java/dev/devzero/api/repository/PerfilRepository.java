package dev.devzero.api.repository;

import org.springframework.stereotype.Repository;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.model.Perfil;

@Repository
public interface PerfilRepository extends BaseRepository<Perfil, Long> {

}