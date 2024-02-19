package api.listResource;

import api.Specifications;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class SortedTest {
    private final static String URL = "https://reqres.in/";
    @Test
    public void sortedYearsTest(){
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecification200());
        List<ListData> listData = given()
                .when()
                .get("api/unknow")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ListData.class);
        List<Integer> years = listData.stream().map(ListData::getYear).collect(Collectors.toList());
        List<Integer>sortedYears = years.stream().sorted().collect(Collectors.toList());

        Assert.assertEquals(sortedYears,years);
        System.out.println(years);
        System.out.println(sortedYears);
    }
}
