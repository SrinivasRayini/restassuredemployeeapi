package com.employeeapi.testCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;

public class TC002_Get_Single_Employee extends TestBase {

	@BeforeClass
	public void getSingleEmployee() throws Exception {
		logger.info("**********Started TC002_Get_Single_Employee ************");

		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employee/"+empID);

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
		logger.info("*********Finished TC002_Get_Single_Employee**************");
	}
	

}
