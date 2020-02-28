package dev.devzero.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.common.BaseService;
import dev.devzero.api.model.Address;
import dev.devzero.api.repository.AddressRepository;

@Service
public class AddressService extends BaseService<Address, Long> {

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public BaseRepository<Address, Long> getRepository() {
		return addressRepository;
	}

}
