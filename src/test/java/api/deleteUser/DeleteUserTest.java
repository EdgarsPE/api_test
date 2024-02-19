package api.deleteUser;

import api.Specifications;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class DeleteUserTest {
    private final static String URL = "https://reqres.in/";

    @Test
    public void deleteTest(){
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecUnique(204));
        given()
                .when()
                .delete("api/users/2")
                .then().log().all();
    }
}
