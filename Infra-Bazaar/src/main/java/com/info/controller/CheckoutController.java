package com.info.controller;

import com.info.dao.CheckoutRepository;
import com.info.model.checkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("checkout")
public class CheckoutController {
    
    @Autowired
    private CheckoutRepository checkoutRepository;

    @PostMapping("checkout")
    public ModelAndView checkout(checkout checkout)
    {
        ModelAndView mv = new ModelAndView("payment");
        checkoutRepository.save(checkout);
        return mv;
    }
}
