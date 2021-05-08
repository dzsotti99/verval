package service;

import domain.Grade;
import domain.Homework;
import domain.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.GradeValidator;
import validation.HomeworkValidator;
import validation.StudentValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTestHomework {

    private static Service serviceBefore;

    @BeforeEach
    void setUp() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Homework> homeworkValidator = new HomeworkValidator();
        Validator<Grade> gradeValidator = new GradeValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "mock/xml/students.xml");
        HomeworkXMLRepository fileRepository2 = new HomeworkXMLRepository(homeworkValidator, "mock/xml/homework.xml");
        GradeXMLRepository fileRepository3 = new GradeXMLRepository(gradeValidator, "mock/xml/grades.xml");

        serviceBefore = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllHomework() {
    }

    @Test
    void saveHomework() {
    }

    @Test
    void deleteHomework() {
    }

    @Test
    void updateHomework() {
    }
}