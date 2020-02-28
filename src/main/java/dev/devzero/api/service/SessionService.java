package dev.devzero.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.common.BaseService;
import dev.devzero.api.model.Session;
import dev.devzero.api.repository.SessionRepository;

@Service
public class SessionService extends BaseService<Session, Long> {

	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public BaseRepository<Session, Long> getRepository() {
		return sessionRepository;
	}

}