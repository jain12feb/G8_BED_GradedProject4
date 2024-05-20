package com.gl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.dto.RoleDto;
import com.gl.entity.Role;
import com.gl.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping
	public Role addRole(@RequestBody RoleDto roleDto) {
		return roleService.addNewRole(roleDto);
	}

}
