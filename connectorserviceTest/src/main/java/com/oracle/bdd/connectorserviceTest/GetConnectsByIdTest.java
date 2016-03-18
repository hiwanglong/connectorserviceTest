package com.oracle.bdd.connectorserviceTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GetConnectsByIdTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	System.out.println("1");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("3");
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		System.out.println("2");
	}

}
