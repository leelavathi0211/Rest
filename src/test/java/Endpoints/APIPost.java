package Endpoints;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import Request.payload.Payload;
public class APIPost {
	public static ResourceBundle getEndpoint(){
		ResourceBundle endpoint=ResourceBundle.getBundle("Config");
		return endpoint;
	}
	public static Response createuser(Payload payload){
		String post_endpoint=getEndpoint().getString("posturl");
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(post_endpoint);
		
		return response;
	}
	public static Response getdetails(Payload payload){
		String get_endpoint=getEndpoint().getString("geturl");
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(get_endpoint);
		
		return response;
	}
	
	
}
