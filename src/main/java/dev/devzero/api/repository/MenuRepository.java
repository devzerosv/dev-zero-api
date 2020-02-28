package dev.devzero.api.repository;

import org.springframework.stereotype.Repository;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.model.Menu;

@Repository
public interface MenuRepository extends BaseRepository<Menu, Long> {

}