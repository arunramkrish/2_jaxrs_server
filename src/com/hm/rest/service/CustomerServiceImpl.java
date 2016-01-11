package com.hm.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.hm.rest.api.CustomerService;
import com.hm.rest.entity.Customer;
import com.hm.rest.entity.Status;

@Path("/customers")
public class CustomerServiceImpl implements CustomerService {
	private static Map<Long, Customer> customersInmemory = new TreeMap<>();

	@POST
	@Produces(MediaType.APPLICATION_XML)
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
//	public Customer createCustomerFromForm(MultivaluedMap<String, String> formParams) {
	
//		public Customer createCustomerFromForm(@Context HttpServletRequest request, @FormParam("name") @DefaultValue("") String name) {
//		Customer c = new Customer();
//		c.setId(customersInmemory.size() + 1L);
//		c.setName(formParams.get("name").get(0));
//		c.setAddress(formParams.get("address").get(0));
//		c.setStatus(Enum.valueOf(Status.class, formParams.get("status").get(0)));
	public Customer createCustomerFromForm(@BeanParam Customer c) {
		c.setId(customersInmemory.size() + 1L);
		customersInmemory.put(c.getId(), c);
		
		return c;
	}

	@Override
	@POST
//	@Produces(MediaType.APPLICATION_XML)
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Customer createCustomer(Customer newCustomer)
			throws MissingAttributeException {
		if (newCustomer.getPhoneNumber() == null) {
			throw new MissingAttributeException(
					"Customer phone number mandatory");
		}
		long newId = customersInmemory.size() + 1L;
		newCustomer.setId(newId);

		customersInmemory.put(newCustomer.getId(), newCustomer);
		return newCustomer;
	}

	@Override
	@GET
	// @Produces(MediaType.APPLICATION_XML)
	public ArrayList<Customer> getCustomers() {
		if (customersInmemory.size() == 0) {
			Customer c = getCustomer();
			c.setId(1L);
			customersInmemory.put(1L, c);
		}
		return new ArrayList(customersInmemory.values());
	}

	private Customer getCustomer() {
		Customer c = new Customer();
		c.setName("Demo1");
		c.setStatus(Status.ACTIVE);
		c.setPhoneNumber("8765857865");

		return c;
	}

	@Override
	@Path("/{cusId}")
	@GET
	public Customer get(@PathParam("cusId") Long id) {
		return customersInmemory.get(id);
	}

	@Override
	@Path("/search")
	@GET
	public List<Customer> search(@QueryParam("searchString") String searchString) {
		List<Customer> matchingCustomers = new ArrayList<>();
		for (Customer c : customersInmemory.values()) {
			if (compareString(c.getName(), searchString)
					|| compareString(c.getAddress(), searchString)
					|| compareString(c.getPhoneNumber(), searchString)) {
				matchingCustomers.add(c);
			}
		}
		return matchingCustomers;
	}

	private boolean compareString(String s1, String s2) {
		if (s1 == null) {
			return false;
		}
		if (s2 == null) {
			s2 = "";
		}
		return s1.toLowerCase().contains(s2.toLowerCase());
	}

	@Override
	@Path("/{id}")
	@PUT
	public Customer update(Customer customer) {
		customersInmemory.put(customer.getId(), customer);
		
		return customer;
	}
	
	@Override
	@DELETE
	@Path("/{id}")
	public Customer delete(@PathParam("id") Long id) {
		return customersInmemory.remove(id);
	}
	
	@Path("/response/{id}/build")
	@GET
	public Response getCustomerResponse(@PathParam("id") Long cusId) {
		Customer c = get(cusId);
		
		ResponseBuilder response = Response.status(javax.ws.rs.core.Response.Status.ACCEPTED);
		response.entity(c);
		response.cookie(new NewCookie("mycookie", "12345"));
		
		return response.build();
	}
}
