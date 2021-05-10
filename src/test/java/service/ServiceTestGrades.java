package service;

import domain.Grade;
import domain.Homework;
import domain.Pair;
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

class ServiceTestGrades {

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

    @Test
    void addGrade1() {
        int res = serviceBefore.saveGrade("2", "1", 8.0, 6, "done");
        // assertEquals(0, res);
        Iterable<Grade> grades = serviceBefore.findAllGrades();

        Grade helper = new Grade(new Pair<>("20", "10"), 10, 7, "good");
        for(Grade grad: grades) {
            if (grad.getID().getObject1().equals("2") && grad.getID().getObject2().equals("1")) {
                helper = grad;
            }
        }

        assertEquals(helper.getID().getObject1(), "2");
        assertEquals(helper.getID().getObject2(), "1");
        assertEquals(helper.getGrade(), 10.5);
        assertEquals(helper.getDeliveryWeek(), 6);
        assertEquals(helper.getFeedback(), "done");
    }

    @Test
    void addGrade2() {
        int res = serviceBefore.saveGrade("5", "1", 8.0, 6, "done");
        assertEquals(-1, res);
        Iterable<Grade> grades = serviceBefore.findAllGrades();

        Grade helper = new Grade(new Pair<>("20", "10"), 10, 7, "good");
        for(Grade grad: grades) {
            if (grad.getID().getObject1().equals("2") && grad.getID().getObject2().equals("1")) {
                helper = grad;
            }
        }

        assertEquals(helper.getID().getObject1(), "20");
        assertEquals(helper.getID().getObject2(), "10");
        assertEquals(helper.getGrade(), 10);
        assertEquals(helper.getDeliveryWeek(), 7);
        assertEquals(helper.getFeedback(), "good");
    }
}