package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;

public class TC003_Post_Employee extends TestBase {

	String empName = RestUtils.empName();
	String empSal = RestUtils.empSal();
	String empAge = RestUtils.empAge();
	
	@BeforeClass
	public void createEmployee() throws Exception {
		logger.info("**********Started TC003_Post_Employee ************");

		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary", empSal);
		requestParams.put("age", empAge);
		
		//set content type
		httpRequest.header("Content-Type","application/json");
		//add the json to the body of the request
		httpRequest.body(requestParams.toJSONString());
		
		
		response = httpRequest.request(Method.POST,"/create");

		Thread.sleep(5000);
	}

	@Test
	void checkResponseBody() {
		logger.info("****** Checking response body********");
		if(response.getStatusCode()==200) {
			String responseBody= response.getBody().asString(); //getting response body
			logger.info("Response body ==> "+ responseBody );
			Assert.assertEquals(responseBody.contains(empName),true);
			Assert.assertEquals(responseBody.contains(empSal),true);
			Assert.assertEquals(responseBody.contains(empAge),true);
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
		logger.info("*********Finished TC003_Post_Employee**************");
	}
	

}
