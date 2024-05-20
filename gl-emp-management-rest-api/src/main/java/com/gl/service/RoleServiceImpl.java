package com.gl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.dto.RoleDto;
import com.gl.entity.Role;
import com.gl.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepo;

	@Override
	public Role addNewRole(RoleDto roleDto) {
		Role role = new Role();
		role.setName(roleDto.getName());
		return roleRepo.save(role);
	}

}
