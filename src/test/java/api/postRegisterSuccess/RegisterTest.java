package api.postRegisterSuccess;

import api.Specifications;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RegisterTest {
    private final static String URL = "https://reqres.in/";

    @Test
    public void successRegistrationTest() {
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecification200());
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Register user = new Register("eve.holt@reqres.in", "pistol");
        SuccessReg successReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(SuccessReg.class);

        // Pārbauda vai atbilde neatnāk tuksa
        Assert.assertNotNull(successReg.getId());
        Assert.assertNotNull(successReg.getToken());

        Assert.assertEquals(id, successReg.getId());
        Assert.assertEquals(token, successReg.getToken());
    }
    @Test
    public void unSuccessRegTest(){
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecification400());
        Register user = new Register("sydney@fife", "");
        UnSuccessReg unSuccessReg = given()
                .body(user)
                .post("api/register")
                .then().log().all()
                .extract().as(UnSuccessReg.class);
        Assert.assertEquals("Missing password", unSuccessReg.getError());

    }

}
