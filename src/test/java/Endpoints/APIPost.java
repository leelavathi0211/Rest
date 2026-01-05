package Endpoints;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import Request.payload.Payload;
public class APIPost {
	//To load the configuration file
	public static ResourceBundle getEndpoint(){
		ResourceBundle endpoint=ResourceBundle.getBundle("Config");
		return endpoint;
	}
	//POST method
	public static Response createuser(Payload payload){
		String post_endpoint=getEndpoint().getString("posturl");
		Response response=given()
		.contentType(ContentType.JSON)
		.body(payload)
		.when()
		.post(post_endpoint);
		
		return response;
	}
	//Get method
	public static Response getdetails(String username){
		String get_endpoint=getEndpoint().getString("geturl");
		Response response=given()
		.pathParam("username", username)
		.contentType(ContentType.JSON)
		.when()
		.get(get_endpoint);

		return response;
	}
	
}
