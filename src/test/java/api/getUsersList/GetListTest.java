package api.getUsersList;

import api.Specifications;
import api.UserData;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetListTest {
    private final static String URL = "https://reqres.in/";
    @Test
    public void checkAvatarAndIdTest() {
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecification200());
        List<api.UserData> users = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", api.UserData.class);
        users.forEach(x-> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assert.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));
        List<String> avatars = users.stream().map(UserData::getAvatar).toList();
        List<String> id = users.stream().map(x->x.getId().toString()).toList();

        for(int i=0; i<avatars.size(); i++){
            Assert.assertTrue(avatars.get(i).contains(id.get(i)));
        }
    }
}
