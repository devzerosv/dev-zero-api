package dev.devzero.api.repository;

import org.springframework.stereotype.Repository;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.model.Session;

@Repository
public interface SessionRepository extends BaseRepository<Session, Long> {

}
