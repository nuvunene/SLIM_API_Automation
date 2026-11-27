package api.Test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.EndPoints.Nac_UserEndPoinits;
import api.PayLoad.User;
import io.restassured.response.Response;

public class UserTests {
	
    Faker faker;
    User userPayload;
    public Logger logger;
    @BeforeClass
    public void setUp()
    {
    	   faker=new Faker();
    	   userPayload=new User();
    	userPayload.setId(faker.idNumber().hashCode());
    	userPayload.setUsername(faker.name().username());
    	userPayload.setFirstName(faker.name().firstName());
    	userPayload.setLastName(faker.name().lastName());
    	userPayload.setEmail(faker.internet().safeEmailAddress());
    	userPayload.setPassword(faker.internet().password(5, 10));
    	userPayload.setPhone(faker.phoneNumber().cellPhone());
    	
    	logger=LogManager.getLogger(this.getClass());
    }
    //postUser script
    @Test(priority = 1)
	public void postUser() {
		logger.info("---------------UserCreation----------------------------");
		Response res = Nac_UserEndPoinits.createUser(userPayload);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("-----------UserCreated----------------");

	}
    @Test(priority = 2)
    public void getUser()
    {
    	logger.info("------------------getUser-------------------------");
    	Response res = Nac_UserEndPoinits.getUser(this.userPayload.getUsername());
    	res.then()
    	   .statusCode(200)
    	   .log().all();
    	logger.info("--------------userFetched-----------------------");
    }
    @Test(priority = 3)
    public void updateUserName()
    {
    	logger.info("----------------UserUpdate----------------------");
    	userPayload.setFirstName(faker.name().firstName());
    	userPayload.setLastName(faker.name().lastName());
    	userPayload.setEmail(faker.internet().safeEmailAddress());
    	Response updateUser = Nac_UserEndPoinits.updateUser(this.userPayload.getUsername(), userPayload);
    	updateUser.then()
    	          .statusCode(200)
    	          .log().all();
    	Response res = Nac_UserEndPoinits.getUser(this.userPayload.getUsername());
    	res.then()
    	   .statusCode(200)
    	   .log().all();
    	logger.info("----------------UserUpdated----------------------");
    	          
    }

    @Test(priority = 4)
    public void deleteUserByName()
    {
    	logger.info("----------------DeleteUser----------------------");
    	Response deleteUser = Nac_UserEndPoinits.deleteUser(this.userPayload.getUsername());
    	deleteUser.then()
    	          .statusCode(200)
    	          .log().all();
    	Response res = Nac_UserEndPoinits.getUser(this.userPayload.getUsername());
    	res.then()
    	   .statusCode(404)
    	   .log().all();
    	logger.info("----------------UserDeleted----------------------");
    }

}
