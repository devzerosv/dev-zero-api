package dev.devzero.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.common.BaseService;
import dev.devzero.api.model.Person;
import dev.devzero.api.repository.PersonRepository;

@Service
public class PersonService extends BaseService<Person, Long> {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public BaseRepository<Person, Long> getRepository() {
		return personRepository;
	}

}