package dev.devzero.api.repository;

import org.springframework.stereotype.Repository;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.model.Address;

@Repository
public interface AddressRepository extends BaseRepository<Address, Long> {

}