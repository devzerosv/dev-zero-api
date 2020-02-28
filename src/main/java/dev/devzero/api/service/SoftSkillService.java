package dev.devzero.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.common.BaseService;
import dev.devzero.api.model.SoftSkill;
import dev.devzero.api.repository.SoftSkillRepository;

@Service
public class SoftSkillService extends BaseService<SoftSkill, Long> {

	@Autowired
	private SoftSkillRepository softSkillRepository;

	@Override
	public BaseRepository<SoftSkill, Long> getRepository() {
		return softSkillRepository;
	}

}