package Tests;

import java.io.IOException;
import java.util.Map;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Endpoints.APIPost;
import Request.payload.Payload;
import Utils.Excel_Testdata;
import io.restassured.path.json.JsonPath;
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
		JsonPath res=response.jsonPath();
		Excel_Testdata.validate(response);
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(res.getString("type"), "unknown");
		Assert.assertEquals(res.getString("message"), ID);
	
	}
	@Test(priority=2,dataProvider="getTestDataTest")
	public void getreq(String ID,String Username,String Firstname,String Lastname,String Email,String Password,String Phone) {		
		
		Response response=APIPost.getdetails(Username);
		response.then().log().all()
		.extract().response();
		JsonPath res=response.jsonPath();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(res.getString("id"),ID);
		Assert.assertEquals(res.getString("username"),Username);
		Assert.assertEquals(res.getString("firstName"),Firstname);
		Assert.assertEquals(res.getString("lastName"),Lastname);
		Assert.assertEquals(res.getString("email"),Email);
		Assert.assertEquals(res.getString("password"),Password);
		Assert.assertEquals(res.getString("phone"),Phone);
		Assert.assertEquals(res.getString("userStatus"),"0");
		Excel_Testdata.validate(response);
		
	}
	

}
