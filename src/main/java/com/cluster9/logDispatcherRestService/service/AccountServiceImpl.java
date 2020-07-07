package com.cluster9.logDispatcherRestService.service;

import com.cluster9.logDispatcherRestService.dao.AppRoleRepo;
import com.cluster9.logDispatcherRestService.dao.AppUserRepo;
import com.cluster9.logDispatcherRestService.entities.AppRole;
import com.cluster9.logDispatcherRestService.entities.AppUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	@Autowired
	private BCryptPasswordEncoder bcEncoder;
	@Autowired
	private AppUserRepo userRepo;
	@Autowired 
	private AppRoleRepo roleRepo;
	@Override
	public AppUser saveUser(AppUser user) {
		String hashPw = bcEncoder.encode(user.getPassword());
		user.setPassword(hashPw);
		return userRepo.save(user);
	}

	@Override
	public AppRole saveRole(AppRole role) {
		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		AppUser user=userRepo.findByIdent(username);
		AppRole role=roleRepo.findByRoleName(roleName);
		user.getRoles().add(role);
	}

	@Override
	public AppUser findUserByUsername(String username) {
		return userRepo.findByIdent(username);
	}

}
