package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplication;
import com.lambdaschool.school.SchoolApplicationTests;
import com.lambdaschool.school.model.Course;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
public class CourseServiceImplTest
{

    @Autowired
    private CourseService courseService;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void findAll()
    {
        assertEquals(6,courseService.findAll().size());
    }

    @Test
    public void getCountStudentsInCourse()
    {
    }

    @Test
    public void deleteFound()
    {
        courseService.delete(3);
        assertEquals(5, courseService.findAll().size());
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteNotFound()
    {
        courseService.delete(45);
    assertEquals(2, courseService.findAll().size());
    }

    @Test
    public void findCourseById()
    {
        assertEquals("Java Back End", courseService.findCourseById(4).getCoursename());
    }

    @Test
    public void save()
    {
        String courseName = "PHP for Beginners";
        Course newCourse = new Course(courseName);

        Course addCourse = courseService.save(newCourse);
        Course foundCourse = courseService.findCourseById(addCourse.getCourseid());
        assertEquals(addCourse.getCoursename(), foundCourse.getCoursename());
    }

}