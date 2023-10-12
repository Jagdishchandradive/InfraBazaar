package com.info.controller;



import java.util.Random;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.info.dao.UserRepository;
import com.info.model.User;
import com.info.service.EmailService;

@Controller
public class ForgotController {
	Random random = new Random(1000);
	@Autowired
	private EmailService emailService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	
	
@RequestMapping("/forgot")
public String openEmailForm() {
		return "forgot_email";
	}


@PostMapping("/send-otp")
public String sendOTP(@RequestParam("email") String email,HttpSession session ) {
	
	System.out.println("EMAIL "+email);
	
	
	
	Random random = new Random();
	int otp = random.nextInt(999999);
	
	System.out.println("OTP "+otp);
	
	String subject = "OTP From InfraBazaar";
	String message =""
			+ "<div style='border:1px solid #e2e2e2; padding:20px'>"
			+  "<h1>"
			+  "OTP is "
			+   "<b>"+otp
			+   "</n>"
			+   "</h1>"
			+   "</div>" ;
	String to=email;
	
	boolean flag = this.emailService.sendEmail(subject, message, to);
	
	if(flag) {
		
		session.setAttribute("myotp", otp);
		session.setAttribute("email", email);
		        return "verify_otp";
	         }  else {
	        	 
	        	 session.setAttribute("message", "Check your email id");
		        return "forgot_email";
	         }
		
		
		
	}
@PostMapping("/verify-otp")
public String verifyOtp(@RequestParam("otp") int otp,HttpSession session) {
	
int myotp=(int)session.getAttribute("myotp");
String email=(String)session.getAttribute("email");

if(myotp==otp)
{
	User user = this.userRepository.findByEmail(email);
	
	if (user==null)
	{
		 session.setAttribute("message", "User does not exist with this email");
	        return "forgot_email";
		
	}else
	{
		
	}
	
	
	
	
	return "password_change";
}else {
	session.setAttribute("message", "You have entered wrong otp");
	return "verify_otp";
}
}
@PostMapping("/change-password")
public String changePassword(@RequestParam("newpassword") String newpassword,HttpSession session)
{
String email = (String)session.getAttribute("email");

User user = this.userRepository.findByEmail(email);

user.setPassword(this.bcrypt.encode(newpassword));
this.userRepository.save(user);

return "redirect:/login?change=password changed successfully";
}

}
