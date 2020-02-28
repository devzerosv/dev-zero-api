package dev.devzero.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.common.BaseService;
import dev.devzero.api.model.UserPerfil;
import dev.devzero.api.repository.UserPerfilRepository;

@Service
public class UserPerfilService extends BaseService<UserPerfil, Long> {

	@Autowired
	private UserPerfilRepository userPerfilRepository;

	@Override
	public BaseRepository<UserPerfil, Long> getRepository() {
		return userPerfilRepository;
	}

}