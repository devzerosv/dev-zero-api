package dev.devzero.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.common.BaseService;
import dev.devzero.api.model.Role;
import dev.devzero.api.repository.RoleRepository;

@Service
public class RoleService extends BaseService<Role, Long> {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public BaseRepository<Role, Long> getRepository() {
		return roleRepository;
	}

}