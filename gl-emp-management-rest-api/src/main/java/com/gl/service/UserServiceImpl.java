package com.gl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gl.dto.UserDto;
import com.gl.entity.Role;
import com.gl.entity.User;
import com.gl.repository.RoleRepository;
import com.gl.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public User addNewUser(UserDto userDto) {
		List<Role> roles = userDto.getRoles().stream().map(role -> roleRepo.findByName(role)).toList();
		User user = User.builder()
						.username(userDto.getUsername())
						.password(encoder.encode(userDto.getPassword()))
						.roles(roles)
						.build();
		
		return userRepo.save(user);
	}

}
