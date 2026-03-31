package base;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class actionMethodAPI {
	
	public static Response postLoginPayload(String payLoad)
	{
		Response response=given()
		.contentType("application/json")
		.body(payLoad)
		.when()
		.post("http://localhost:5000/api/login");
		return response;
	}
	
	public static Response postCreatePayload(String payLoad)
	{
		Response response=given()
		.contentType("application/json")
		.body(payLoad)
		.when()
		.post("http://localhost:5000/api/users");
		return response;	
	}

}
