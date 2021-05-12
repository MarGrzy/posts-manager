package marcin.grzymowicz.posts_manager.posts

import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.RestAssuredMockMvc
import marcin.grzymowicz.posts_manager.posts.domain.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.test.context.jdbc.SqlMergeMode
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification
import spock.lang.Unroll

import static io.restassured.RestAssured.given

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SqlGroup([
        @Sql(scripts = ["classpath:sql/base.sql"], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
])
@TestPropertySource(locations = "classpath:application-test.properties")
@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
class PostIntegrationSpec extends Specification {

    @LocalServerPort
    int port
    @Autowired
    private WebApplicationContext webApplicationContext

    @Autowired
    private PostRepository postRepository

    void setup() {
        RestAssured.port = port
        RestAssuredMockMvc.standaloneSetup(webApplicationContext)
    }

    @Unroll
    def "should not allow to update post, because #testName"() {
        given:
        Map updatePostCmd = [
                id   : id,
                title: title,
                body : body,
        ]
        when:
        def result =
                given()
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .body(updatePostCmd)
                        .when()
                        .post("/api/posts/update")
        then:
        result.then().statusCode(statusCode)

        for (Object matcher : matchers) {
            result.then().body(matcher[0], matcher[1])
        }

        where:
        testName          || id   || title  || body          || statusCode || matchers
        "Null id"         || null || "test" || "Testy testu" || 400        || [["Entity not found in database!"]]
        "Id not positive" || 0    || "test" || "Testy testu" || 400        || [["Id cannot be negative value!"]]
        "Null title"      || 1    || null   || "Testy testu" || 400        || [["Title cannot be null!"]]
        "Blank title"     || 1    || ""     || "Testy testu" || 400        || [["Title cannot be empty!"]]
        "Null body"       || 1    || "test" || null          || 400        || [["Body cannot be null!"]]
        "Blank body"      || 1    || "test" || ""            || 400        || [["Body cannot be empty"]]
    }
}
