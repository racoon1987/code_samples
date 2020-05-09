package com.sample.boot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.boot.model.Account;

// boot/sample
@RestController()
@RequestMapping("/sample")
public class ModelAttributeController {

	/**
	 * A controller can have any number of '@ModelAttribute' methods
	 * '@ModelAttribute' method helps to initialize the model prior to any
	 * '@RequestMapping' method invocation
	 */
	
	// init model attribute
	@ModelAttribute(name = "account1")
	public Account populateModel2(@RequestParam String number) {
		return new Account(2L, "Jade", number);
	}

	// init model attribute
	@ModelAttribute
	public void populateModel(@RequestParam String number, Model model) {
		Account account = new Account(1L, "James", number);

		model.addAttribute("account2", account);
	}

	@GetMapping("/account")
	public List<Account> getInitializedModels(Model model) {
		List<Account> listAcc = new ArrayList<>();
		Account acc1 = (Account) model.getAttribute("account1");
		Account acc2 = (Account) model.getAttribute("account2");

		listAcc.add(acc1);
		listAcc.add(acc2);

		return listAcc;
	}

	// auto parse fields from post’s params, @ModelAttribute here can be omitted
	// without any change in this method behaviour
	@PostMapping("/account/{id}/{name}/{number}")
	public Account processSubmit(@ModelAttribute Account account) {
		return account;
	}

	// automatically apply validation after data binding
	@PostMapping("/account/validate-add/{id}/{name}/{number}")
	public Account processSubmitwithvalidation(@Valid @ModelAttribute Account account) {
		return account;
	}
}
