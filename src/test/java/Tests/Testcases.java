package Tests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Endpoints.APIPost;
import Request.payload.Payload;
import Utils.Excel_Testdata;
import io.restassured.config.Config;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Testcases {
	String sheetName="Test";
	//dataprovider from excel 
	@DataProvider
	public Object[][] getTestDataTest() throws EncryptedDocumentException, IOException {
		Object data[][]=Excel_Testdata.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=1,dataProvider="getTestDataTest")
	public void postreq(String ID,String Username,String Firstname,String Lastname,String Email,String Password,String Phone) {
		Payload input=new Payload();
		input.setId(Integer.parseInt(ID));
		input.setUsername(Username);
		input.setFirstName(Firstname);
		input.setLastName(Lastname);
		input.setEmail(Email);
		input.setPassword(Password);
		input.setPhone(Phone);
		
		
		Response response=APIPost.createuser(input);
		response.then().log().all()
		.extract().response();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("type"), "unknown");
		Assert.assertEquals(response.jsonPath().getString("message"), ID);
		Assert.assertEquals(response.getHeader("Content-Type"), "application/json");
		Assert.assertTrue(response.getHeader("Server").contains("Jetty"));
		Assert.assertTrue(response.getHeader("Connection").contains("keep-alive"));
		Assert.assertTrue(response.getHeader("Transfer-Encoding").contains("chunked"));
		Assert.assertNotNull(response.getHeader("Date"));		
	}
	@Test(priority=2,dataProvider="getTestDataTest")
	public void getreq(String ID,String Username,String Firstname,String Lastname,String Email,String Password,String Phone) {
		Payload input=new Payload();
		input.setId(Integer.parseInt(ID));
		input.setUsername(Username);
		input.setFirstName(Firstname);
		input.setLastName(Lastname);
		input.setEmail(Email);
		input.setPassword(Password);
		input.setPhone(Phone);
		
		
		Response response=APIPost.getdetails(Username);
		response.then().log().all()
		.extract().response();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("id"),ID);
		Assert.assertEquals(response.jsonPath().getString("username"),Username);
		Assert.assertEquals(response.jsonPath().getString("firstName"),Firstname);
		Assert.assertEquals(response.jsonPath().getString("lastName"),Lastname);
		Assert.assertEquals(response.jsonPath().getString("email"),Email);
		Assert.assertEquals(response.jsonPath().getString("password"),Password);
		Assert.assertEquals(response.jsonPath().getString("phone"),Phone);
		Assert.assertEquals(response.jsonPath().getString("userStatus"),"0");
		Assert.assertEquals(response.getHeader("Content-Type"), "application/json");
		Assert.assertTrue(response.getHeader("Server").contains("Jetty"));
		Assert.assertTrue(response.getHeader("Connection").contains("keep-alive"));
		Assert.assertTrue(response.getHeader("Transfer-Encoding").contains("chunked"));
		Assert.assertNotNull(response.getHeader("Date"));	
		
	}
	
}
