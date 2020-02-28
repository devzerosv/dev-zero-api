package dev.devzero.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.common.BaseService;
import dev.devzero.api.model.Menu;
import dev.devzero.api.repository.MenuRepository;

@Service
public class MenuService extends BaseService<Menu, Long> {

	@Autowired
	private MenuRepository menuRepository;

	@Override
	public BaseRepository<Menu, Long> getRepository() {
		return menuRepository;
	}

}