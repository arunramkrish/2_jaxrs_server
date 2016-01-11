package com.bt.rest.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.SslConfigurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hm.rest.entity.Customer;
import com.hm.rest.entity.Status;

public class CustomerServiceTest {

	private static WebTarget cusTarget;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		SslConfigurator sslconfig = SslConfigurator.newInstance();
		SSLContext sslContext = sslconfig.createSSLContext();
		
		Client client = ClientBuilder.newBuilder().sslContext(sslContext).build();

		WebTarget base = client
				.target("https://localhost:8080/2_jaxrs_server/api");

		cusTarget = base.path("customers");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateCustomer() {
		Customer newCustomer = getCustomer();
		
		Invocation.Builder invocation = cusTarget.request(MediaType.APPLICATION_XML);
		Response response = invocation.post(Entity.entity(newCustomer, 
				MediaType.APPLICATION_XML));
		Customer fromServer = response.readEntity(Customer.class);
		
		System.out.println("Customer created " + fromServer.getId());
		
	}

	@Test
	public void testGet() {
		WebTarget searchTarget = cusTarget.path("1");
		
		Invocation.Builder invocation = searchTarget.request(MediaType.APPLICATION_XML);
		Response response = invocation.get();
		Customer fromServer = 
				response.readEntity(Customer.class);
		
		System.out.println("Customer get with id" + fromServer.getName());
	}

	@Test
	public void testSearch() {
		WebTarget searchTarget = cusTarget.path("search").queryParam("searchString", "demo");
		
		Invocation.Builder invocation = searchTarget.request(MediaType.APPLICATION_XML);
		Response response = invocation.get();
		List<Customer> fromServer = 
				response.readEntity(new GenericType<List<Customer>>(){});
		
		System.out.println("Customer search " + fromServer.size());
		
	}

	private static Customer getCustomer() {
		Customer c = new Customer();
		c.setName("Demo1");
		c.setStatus(Status.ACTIVE);
		c.setPhoneNumber("8765857865");

		return c;
	}

}
