package dev.devzero.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.common.BaseService;
import dev.devzero.api.model.Company;
import dev.devzero.api.repository.CompanyRepository;

@Service
public class CompanyService extends BaseService<Company, Long> {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public BaseRepository<Company, Long> getRepository() {
		return companyRepository;
	}

}