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
	}
	@Test(priority=0,dataProvider="getTestDataTest")
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
//		Assert.assertEquals(response.jsonPath().getString("type"), "unknown");
//		Assert.assertEquals(response.jsonPath().getString("message"), ID);
	}
	
}
