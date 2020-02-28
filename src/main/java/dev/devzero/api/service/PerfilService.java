package dev.devzero.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.common.BaseService;
import dev.devzero.api.model.Perfil;
import dev.devzero.api.repository.PerfilRepository;

@Service
public class PerfilService extends BaseService<Perfil, Long> {

	@Autowired
	private PerfilRepository perfilRepository;

	@Override
	public BaseRepository<Perfil, Long> getRepository() {
		return perfilRepository;
	}

}