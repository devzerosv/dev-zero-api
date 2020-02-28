package dev.devzero.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.common.BaseService;
import dev.devzero.api.model.RolMenu;
import dev.devzero.api.repository.RolMenuRepository;

@Service
public class RolMenuService extends BaseService<RolMenu, Long> {

	@Autowired
	private RolMenuRepository rolMenuRepository;

	@Override
	public BaseRepository<RolMenu, Long> getRepository() {
		return rolMenuRepository;
	}

}