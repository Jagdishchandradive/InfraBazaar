package com.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.info.service.UserService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("index")
	public String index() {
		return "admin/index";
	}
	
	@GetMapping("user-list")
	public String userList(Model model) {
		model.addAttribute("userList", userService.findAllUser());
		return "admin/user-list";
	}
	
	@GetMapping("delete-User/{userId}")
	public ModelAndView deleteUser(@PathVariable("userId")String userId) {
		ModelAndView mv = new ModelAndView("admin/user-list");
		userService.deleteUser(Long.parseLong(userId));
		mv.addObject("userList", userService.findAllUser());
		return mv;
	}
	
	@GetMapping("add-user")
	public String addUser() {
		return "admin/add-user";
	}

}
