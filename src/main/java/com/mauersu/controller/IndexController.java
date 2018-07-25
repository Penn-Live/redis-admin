package com.mauersu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mauersu.util.Constant;
@Controller
public class IndexController implements Constant{


	@Autowired
	Environment env;

	@RequestMapping(value="/index", method=RequestMethod.GET)
	public Object index(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		setUser(session);
		return "redirect:/redis";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public Object home(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		setUser(session);
		return "redirect:/redis";
	}

	@RequestMapping(value="/logingout", method=RequestMethod.GET)
	public Object logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return "accessDenied";
	}




	private void setUser(HttpSession session){
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		User user = (User) auth.getPrincipal();
		session.setAttribute("isAdmin",env.getProperty("manager.username").equals(user.getUsername()));

	}
}
