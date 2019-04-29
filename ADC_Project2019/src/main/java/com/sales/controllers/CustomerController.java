package com.sales.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sales.models.Customer;
import com.sales.services.CustomerService;

@Controller
public class CustomerController
{
	@Autowired
	CustomerService customerservice;
	
	@RequestMapping(value="/showCustomers", method=RequestMethod.GET)
	public String getCustomers(Model m)
	{
		ArrayList<Customer> customers = customerservice.findCustomers();
		m.addAttribute("customers", customers);
		return "showCustomers";
	}// getCustomers
	
	@RequestMapping(value="/addCustomer", method=RequestMethod.GET)
	public String addCustomerGet(@ModelAttribute("customerAdd") Customer customer, HttpServletRequest h)
	{
		return "addCustomer";
	}// addCustomerGet
	
	@RequestMapping(value="/addCustomer", method=RequestMethod.POST)
	public String addCustomerPost(@Valid @ModelAttribute("customerAdd") Customer customer, BindingResult res)
	{
		if(res.hasErrors())
			return "addCustomer";
		
		customerservice.saveCustomer(customer);
		return "redirect:showCustomers";
	}// addCustomerPost
}// CustomerController
