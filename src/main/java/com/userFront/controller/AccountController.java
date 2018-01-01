package com.userFront.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.userFront.domain.PrimaryAccount;
import com.userFront.domain.SavingsAccount;
import com.userFront.domain.User;
import com.userFront.service.AccountService;
import com.userFront.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private AccountService  accountService;

	@RequestMapping("/primaryAccount")
	public String primaryAccount(Model model, Principal principal ) {
	
		User user = userService.findByUsername(principal.getName());
        PrimaryAccount primaryAccount = user.getPrimaryAccount();

        model.addAttribute("primaryAccount", primaryAccount);
		
		return "primaryAccount";
	}
	
	
	@RequestMapping("/savingsAccount")
    public String savingsAccount(Model model, Principal principal) {
		
		 User user = userService.findByUsername(principal.getName());
	     SavingsAccount savingsAccount = user.getSavingsAccount();

	        model.addAttribute("savingsAccount", savingsAccount);
		
		
		return "savingsAccount";
	}
	
	
	
	@RequestMapping(value = "/deposit", method = RequestMethod.GET)
    public String deposit(Model model) {
        model.addAttribute("accountType", "");
        model.addAttribute("amount", "");

        return "deposit";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String depositPOST(@ModelAttribute("amount") String amount, @ModelAttribute("accountType") String accountType, Principal principal) {
       
        System.out.println("hello3"+amount);
    	accountService.deposit(accountType, Double.parseDouble(amount), principal);

        return "redirect:/userFront";
    }
	
	
}
