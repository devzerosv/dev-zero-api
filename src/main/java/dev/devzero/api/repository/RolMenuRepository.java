package dev.devzero.api.repository;

import org.springframework.stereotype.Repository;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.model.RolMenu;

@Repository
public interface RolMenuRepository extends BaseRepository<RolMenu, Long> {

}