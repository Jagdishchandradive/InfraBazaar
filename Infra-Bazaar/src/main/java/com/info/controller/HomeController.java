package com.info.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.info.dao.UserRepository;
import com.info.model.*;

import com.info.service.CategoryService;
import com.info.service.ProductService;
import com.info.service.UserService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserRepository userRepository;

	@GetMapping({ "/index" })
	public String index(Model model) {
		model.addAttribute("categoryList", categoryService.listCategory());
		model.addAttribute("productList", productService.listProduct());
		return "index";
	}

	@GetMapping("login")
	public String login() {
		return "login";
	}

	// ===========================================================================
	@PostMapping("login")
	public ModelAndView login(User user) {
		ModelAndView mv = new ModelAndView("/index");
		userService.save(user);
		// mv.addObject("productList", productService.listProduct());
		// mv.addObject("categoryList", categoryService.listCategory());
		return mv;
	}

	// ==========================================================================================
	@GetMapping("signup")
	public String signup() {
		return "signup";
	}

	@PostMapping("signup")
	public ModelAndView signUp(User user) {
	userService.save(user);
	ModelAndView mv = new ModelAndView("/index");
	mv.addObject("productList", productService.listProduct());
	mv.addObject("categoryList", categoryService.listCategory());
	return mv;
	}
	// @PostMapping("signup")
	// public String signup(@RequestParam String email, User user, HttpSession session) {
	// 	User user1 = this.userRepository.findByEmail(email);

	// 	try {
	// 		if (user.getEmail().equals(user1.getEmail())) {
	// 			session.setAttribute("message", "User is exist with this email");
	// 			return "signup";
	// 		}
	// 	} catch (Exception e) {
	// 		userService.save(user);
	// 		session.setAttribute("message", "registration is successfull !!! ");

	// 		return "signup";
	// 	}
	// 	return "";
	// }

	// @PostMapping("user_registration")
	// public String user_registration(@Valid @ModelAttribute("User") User user,
	// BindingResult result)
	// {
	// if(result.hasErrors())
	// {
	// System.out.println(result);
	// return "user_registration";
	// }
	// User user1 = this.userRepository.findByEmail(email);
	//
	// try {
	// if (user.getEmail().equals(user1.getEmail())){
	// session.setAttribute("message", "User is exist with this email");
	// return "user_registration";
	// }
	// }catch(Exception e) {
	// userService.save(user);
	// session.setAttribute("message", "registration is successfull !!! please login
	// ");
	//
	// return "login";
	// }
	// return "login";
	// }

	@PostMapping("user_registration")
	public String user_registration(@RequestParam String email, User user, HttpSession session) {
		User user1 = this.userRepository.findByEmail(email);

		try {
			if (user.getEmail().equals(user1.getEmail())) {
				session.setAttribute("message", "User is exist with this email");
				return "user_registration";
			}
		} catch (Exception e) {
			userService.save(user);
			session.setAttribute("message", "registration is successfull !!! please login ");

			return "login";
		}
		return "";
	}

	@GetMapping("user_registration")
	public String user_registration() {
		return "user_registration";
	}

	@GetMapping("allProduct")
	public String allProduct(Model model) {
		model.addAttribute("productList", productService.listProduct());
		model.addAttribute("categoryList", categoryService.listCategory());
		return "index";
	}

	@GetMapping("getProducts/{categoryId}")
	public ModelAndView getProductFromCategory(@PathVariable("categoryId") String categoryId) {
		ModelAndView mv = new ModelAndView("index");
		long categoryLongId = Long.parseLong(categoryId);
		System.out.println(categoryLongId);
		mv.addObject("productList", productService.findByCategory(categoryLongId));
		mv.addObject("categoryList", categoryService.listCategory());
		return mv;
	}

	@GetMapping("error")
	public String error() {
		return "error";
	}

	// =================== jagdish====================
	// @RequestMapping("/")

	@GetMapping({ "home", "/" })
	public String home(Model model) {
		model.addAttribute("title", "Infra-Bazar");
		return "home";
	}

	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "About");
		return "about";
	}

	// ==============================================================

	// ========================= check_out ==========================
	@GetMapping("/checkout")
	public String checkout(Model model) {
		model.addAttribute("total", "checkout");
		return "checkout";
	}
}
