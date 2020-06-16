package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.vo.User;
@Service
public class UserSerivceImpl implements UserService {

	  @Autowired
	  private UserMapper usermapper;
	@Override
	public User findByUserName(String username) {
		return usermapper.findByUserName(username);
	}

}
