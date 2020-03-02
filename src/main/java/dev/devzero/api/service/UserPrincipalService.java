package dev.devzero.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.devzero.api.common.BaseRepository;
import dev.devzero.api.common.BaseService;
import dev.devzero.api.model.UserPrincipal;
import dev.devzero.api.repository.UserPrincipalRepository;

@Service
public class UserPrincipalService extends BaseService<UserPrincipal, Long> {

	@Autowired
	private UserPrincipalRepository userPrincipalRepository;

	@Override
	public BaseRepository<UserPrincipal, Long> getRepository() {
		return userPrincipalRepository;
	}

//	public List<UserPrincipal> findUserList() {
//
//		OrderSpecifier<String> order = QUserPrincipal.userPrincipal.name.asc();
//
//		List<UserPrincipal> userList = (List<UserPrincipal>) userPrincipalRepository.findAll(order);
//
//		return userList;
//	}

}
