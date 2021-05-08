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

    @Test
    void addHomework1() {
        serviceBefore.saveHomework("100", "Foarte greu", 20, 10);

        serviceBefore.saveStudent("100", "Zsolt", 532);
        Iterable<Student> studs = serviceBefore.findAllStudents();
        Student helper = new Student("101", "Not Zsolt", 533);
        for (Student stud : studs) {
            if(stud.getID().equals("100")) {
                helper = stud;
            }
        }
        assertEquals(helper.getID(), "100");
        assertEquals(helper.getGroup(), 532);
        assertEquals(helper.getName(), "Zsolt");
        serviceBefore.deleteStudent("100");
    }

    @Test
    void addHomework2() {
        serviceBefore.saveStudent("100", "Zsolt", 532);
        Iterable<Student> studs = serviceBefore.findAllStudents();
        Student helper = new Student("101", "Not Zsolt", 533);
        for (Student stud : studs) {
            if(stud.getID().equals("100")) {
                helper = stud;
            }
        }
        assertEquals(helper.getID(), "100");
        assertEquals(helper.getGroup(), 532);
        assertEquals(helper.getName(), "Zsolt");
        serviceBefore.deleteStudent("100");
        studs = serviceBefore.findAllStudents();
        helper = new Student("101", "Not Zsolt", 533);
        for (Student stud : studs) {
            if(stud.getID().equals("100")) {
                helper = stud;
            }
        }
        assertEquals(helper.getID(), "101");
        assertEquals(helper.getGroup(), 533);
        assertEquals(helper.getName(), "Not Zsolt");
    }

    @Test
    void addHomework3() {
        Iterable<Student> studs = serviceBefore.findAllStudents();
        int counter1 = 0;
        for (Student stud : studs) {
            counter1++;
        }
        serviceBefore.saveStudent("102", "Zsolt", 532);
        serviceBefore.saveStudent("102", "Zsolt", 532);
        studs = serviceBefore.findAllStudents();
        int counter2 = 0;
        for (Student stud : studs) {
            counter2++;
        }
        assertEquals(counter2, counter1 + 1);
        serviceBefore.deleteStudent("102");
    }
}