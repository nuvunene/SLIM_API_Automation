package api.Test;

import org.testng.annotations.Test;

import api.EndPoints.Nac_UserEndPoinits;
import api.PayLoad.User;
import api.Utillities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenCreateUser {

	@Test(priority = 1,dataProvider = "Data",dataProviderClass =DataProviders.class)
	public void createUser(String id,String username,String firstName,String lastName,String email,String password,String phone,String status)
	{
         User payload=new User();	
         payload.setId(Integer.parseInt(id));
         payload.setUsername(username);
         payload.setFirstName(firstName);
         payload.setLastName(lastName);
         payload.setEmail(email);
         payload.setPassword(password);
         payload.setPhone(phone);
         payload.setUserStatus(Integer.parseInt(status));
         
         Response response = Nac_UserEndPoinits.createUser(payload);
         response.then()
                 .statusCode(200)
                 .log().all();
         
	}
	@Test(priority = 2, dataProvider="userName",dataProviderClass =DataProviders.class)
	public void deleteByUserName(String username)
	{
		Response response=Nac_UserEndPoinits.deleteUser(username);
		response.then()
		        .statusCode(200)
		        .log().all();
		Response response1=Nac_UserEndPoinits.getUser(username);
		response1.then()
		         .statusCode(404)
		         .log().all();
	}
	
}
