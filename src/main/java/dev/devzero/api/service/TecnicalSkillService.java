package dev.devzero.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.common.BaseService;
import dev.devzero.api.model.TecnicalSkill;
import dev.devzero.api.repository.TecnicalSkillRepository;

@Service
public class TecnicalSkillService extends BaseService<TecnicalSkill, Long> {

	@Autowired
	private TecnicalSkillRepository tecnicalSkillRepository;

	@Override
	public BaseRepository<TecnicalSkill, Long> getRepository() {
		return tecnicalSkillRepository;
	}

}