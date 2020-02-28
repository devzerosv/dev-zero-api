package dev.devzero.api.repository;

import org.springframework.stereotype.Repository;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.model.Company;

@Repository
public interface CompanyRepository extends BaseRepository<Company, Long> {

}