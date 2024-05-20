package com.gl.service;

import com.gl.dto.UserDto;
import com.gl.entity.User;

public interface UserService {
	
	User addNewUser(UserDto userDto);

}
