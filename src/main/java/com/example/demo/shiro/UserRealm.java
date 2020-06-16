package com.example.demo.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.service.UserService;
import com.example.demo.vo.User;

public class UserRealm extends AuthorizingRealm {

	
	//执行授权逻辑
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.err.println("执行授权逻辑");
		
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		info.addStringPermission("pages:add");
		return info;
	}

	
	@Autowired
	private UserService userService;
	
	//执行认证逻辑
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		System.err.println("执行认证逻辑 ");
		
		//1.判断用户名
		UsernamePasswordToken token=(UsernamePasswordToken)arg0;
	User user=	userService.findByUserName(token.getUsername());
		
	if(user==null)
		{
		//用户不存在
			return null;//
		}
		//判断密码
		return new SimpleAuthenticationInfo("",user.getPassword(),"");
		
	}

}
