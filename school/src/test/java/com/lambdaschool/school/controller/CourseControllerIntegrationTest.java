package com.lambdaschool.school.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import com.lambdaschool.school.model.Course;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.number.OrderingComparison.lessThan;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseControllerIntegrationTest
{
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void initialiseRestAssuredMockMvcWebApplicationContext()
    {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @Test
    public void whenMeasuredReponseTime()
    {
        given().when().get("/courses/courses").then().time(lessThan(5000L));
    }

    @Test
    public void givenPostACourse() throws Exception
    {
        String courseName = "IntergrationTest";
        Course newCourse = new Course(courseName);

        ObjectMapper mapper = new ObjectMapper()
        {
        };
        String stringCourse = mapper.writeValueAsString(newCourse);

        given().contentType("application/json").body(stringCourse).when().post("/courses/course/add").then().statusCode(201);
    }
}
