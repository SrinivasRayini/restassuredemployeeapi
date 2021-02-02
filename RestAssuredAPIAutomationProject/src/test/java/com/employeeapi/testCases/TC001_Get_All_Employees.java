package com.employeeapi.testCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;

public class TC001_Get_All_Employees extends TestBase {

	@BeforeClass
	public void getAllEmployees() throws Exception {
		logger.info("**********Started TC001_Get_All_Employees ************");

		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employees");

		Thread.sleep(5000);
	}

	@Test
	void checkResponseBody() {
		logger.info("****** Checking response body********");
		if(response.getStatusCode()==200) {
			String responseBody= response.getBody().asString(); //getting response body
			logger.info("Response body ==> "+ responseBody );
			Assert.assertTrue(responseBody!=null);
		}
		else {
			logger.info("********Failed to get the response*******");
			Assert.assertTrue(false);
		}
	}

	@Test
	void checkStatusCode() {
		logger.info("**********Checking status code****************");
		int statusCode= response.getStatusCode(); //getting status code
		logger.info("Status code==> "+ statusCode); //200
		Assert.assertEquals(200, statusCode);
	}

	@AfterClass
	void tearDown() {
		logger.info("*********Finished TC001_Get_All_Employees**************");
	}
	

}
