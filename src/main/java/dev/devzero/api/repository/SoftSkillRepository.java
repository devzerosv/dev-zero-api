package dev.devzero.api.repository;

import org.springframework.stereotype.Repository;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.model.SoftSkill;

@Repository
public interface SoftSkillRepository extends BaseRepository<SoftSkill, Long> {

}