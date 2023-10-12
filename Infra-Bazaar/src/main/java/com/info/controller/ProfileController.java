package com.info.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.info.dao.paymentRepository;
import com.info.model.Product;
import com.info.model.*;
import com.info.service.IEmailSenderService;
import com.info.service.ProductService;
import com.info.service.*;

@Controller
@RequestMapping("profile")
public class ProfileController {

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private IEmailSenderService  emailSenderService;
	
	@Autowired
	private paymentRepository paymentRepository;
	
	@GetMapping("cart-product")
	public ModelAndView cartProduct(Principal principal)  throws InterruptedException{
		ModelAndView mv = new ModelAndView("profile/cart-product");
		User user = userService.findByEmail(principal.getName());
		mv.addObject("user", user);
		int total = findSum(user);
		mv.addObject("total", total);
		return mv;
	}

	
	private int findSum(User user) throws MailException , InterruptedException{
		List<Product> list = user.getProductList();
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			Product p = list.get(i);
			sum += p.getProductPrice();
		}
		return sum;
	}

	@GetMapping("checkout")
	public ModelAndView cartProduct1(Principal principal) throws InterruptedException {
		ModelAndView mv = new ModelAndView("checkout");
		User user = userService.findByEmail(principal.getName());
		String email = user.getEmail();
		mv.addObject("user", user);
		int total = findSum(user);
		mv.addObject("email", email);
		mv.addObject("total", total);
		mv.addObject("GSTtotal", total + total * 0.18);

		return mv;
	}
	
	@PostMapping("placeorder")
	public ModelAndView cartProduct2(Principal principal , payment payment) throws  InterruptedException {
		ModelAndView mv = new ModelAndView("placeorder");
		paymentRepository.save(payment);
		User user = userService.findByEmail(principal.getName());
		String email = user.getEmail();
		long id = user.getUserId();
		mv.addObject("user", user);
		int total = findSum(user);
		double gstamt = total+total*0.18;
		mv.addObject("total", total);
		mv.addObject("email" , email);
		mv.addObject("id" , id);
		mv.addObject("GSTtotal", total+total*0.18);
		emailSenderService.sendSimpleEmail(user.getEmail(),
				"Dear " + user.getFirstName() + " " + user.getLastName() + ",\n\n"
						+ "Congratulations! Your order is successfully placed.\n\n"
						+ "paybel amount : "+gstamt
						+ "\n\n"+"order id : "+user.getUserId()
						+ "\n"
						+"\n"
						+ "\n"+"THANK YOU !!" +"\n"+ "Warm Regards,\n" + "Infra Bazaar,\n" + "CDAC STDC Thiruanantapuram SERVICES",
				"Payment Successfull");
		return mv;
	}
	@GetMapping("payment")
	public ModelAndView payment(Principal principal) throws  InterruptedException {
		ModelAndView mv = new ModelAndView("payment");		
		return mv;
	}

	@GetMapping("addToCart/{productId}")
	public ModelAndView addToCart(@PathVariable("productId") String productId, Principal principal) throws  InterruptedException {
		ModelAndView mv = new ModelAndView("profile/cart-product");
		User user = userService.findByEmail(principal.getName());
		long productLongId = Long.parseLong(productId);
		Product product = productService.getProductById(productLongId).get();

		List<Product> productList = new ArrayList<Product>();
		productList.add(product);
		user.setProductList(productList);

		List<User> userList = new ArrayList<>();
		userList.add(user);
		product.setUserList(userList);

		userService.update(user);
		productService.addProduct(product);
		int total = findSum(user);
		mv.addObject("total", total);
		mv.addObject("user", user);
		return mv;
	}

}
