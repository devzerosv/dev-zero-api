package dev.devzero.api.repository;

import org.springframework.stereotype.Repository;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.model.User;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

}