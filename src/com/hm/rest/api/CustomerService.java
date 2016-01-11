package com.hm.rest.api;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;

import com.hm.rest.entity.Customer;
import com.hm.rest.service.MissingAttributeException;

@WebService
@SOAPBinding(parameterStyle=ParameterStyle.WRAPPED, style=Style.RPC)
public interface CustomerService {
	Customer createCustomer(Customer newCustomer) throws MissingAttributeException;
	
	ArrayList<Customer> getCustomers();
	
	Customer get(Long id);
	
	List<Customer> search(String searchString);
	
	Customer update(Customer customer);

	Customer delete(Long id);
}
