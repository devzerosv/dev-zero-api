package dev.devzero.api.repository;

import org.springframework.stereotype.Repository;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.model.Person;

@Repository
public interface PersonRepository extends BaseRepository<Person, Long> {

}