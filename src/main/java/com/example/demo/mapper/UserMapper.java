package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.User;
@Mapper
public interface UserMapper {

	    public User findByUserName(String username);
}
