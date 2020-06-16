package com.example.demo.controller;



import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class PageController {
	

	@RequestMapping("/test")
	
	public String doIndexUI() {
		return "test";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "add";
		
	}
	@RequestMapping("/update")
	public String update() {
		return "update";
	}
	
	@RequestMapping("/tologin")
	public String tologin() {
		return "login";
	}
	
	@RequestMapping("/login")
	public String login(String username,String password,Model model) {
		System.err.println("username"+username);
		//1.获取subject
		Subject subject = SecurityUtils.getSubject();
		 //2.封装用户数据
		 UsernamePasswordToken token=new UsernamePasswordToken(username,password);
		 //3.执行登录方法
		 try {
			 subject.login(token);
			 //登录成功
			 //跳转到test界面
			 return "test";
		} catch (UnknownAccountException e) {
			model.addAttribute("msg","用户名不存在");
			return "login";
		}catch (IncorrectCredentialsException e) {
			//登录失败，密码错误
			model.addAttribute("msg","密码错误");
			return "login";
		}
	}
	
	@RequestMapping("/noAuth")
	public String noAuth() {
		return "noAuth";
	}
	
	
	
	
	
	
}
