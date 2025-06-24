package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Student;
import com.example.request.CreateStudentRequest;
import com.example.request.InQueryRequest;
import com.example.request.UpdateStudentRequest;
import com.example.response.StudentResponse;
import com.example.service.StudentService;

@RestController()
@RequestMapping("/api/student")
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentService studentService;

    @GetMapping("/getAll")    // This is the endpoint for the getAllStudents method
    public List<StudentResponse> getAllStudents() {
        // logger.info("getAllStudents method info");
        // logger.error("getAllStudents method error");
        // logger.warn("getAllStudents method warn");
        // logger.debug("getAllStudents method debug");
        // logger.trace("getAllStudents method trace");
        List<Student> students = studentService.getAllStudents();
        List<StudentResponse> studentResponses = new ArrayList<StudentResponse>();
        for (Student student : students) {
            studentResponses.add(new StudentResponse(student));
        }
        return studentResponses;
    }

    @PostMapping("/create")
    public StudentResponse createStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest) {
        Student student = studentService.createStudent(createStudentRequest);
        return new StudentResponse(student);
    }

    @PostMapping("/createAll")
    public List<StudentResponse> createAllStudents(@Valid @RequestBody List<CreateStudentRequest> createStudentRequests) {
        List<Student> students = studentService.createAllStudents(createStudentRequests);
        List<StudentResponse> studentResponses = new ArrayList<StudentResponse>();
        for (Student student : students) {
            studentResponses.add(new StudentResponse(student));
        }
        return studentResponses;
    }

    @PutMapping("/update")
    public StudentResponse updateStudent(@Valid @RequestBody UpdateStudentRequest updateStudentRequest) {
        Student student = studentService.updateStudent(updateStudentRequest);
        return new StudentResponse(student);
    }

    @DeleteMapping("/delete")
    public String deleteStudent1(@RequestParam Long id) {
        studentService.deleteStudent(id);
        return "Student deleted successfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent2(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Student deleted successfully";
    }

    @GetMapping("/getByFirstName/{firstName}")
    public List<StudentResponse> getByFirstName(@PathVariable String firstName) {
        List<Student> students = studentService.getByFirstName(firstName);
        List<StudentResponse> studentResponses = new ArrayList<StudentResponse>();
        for (Student student : students) {
            studentResponses.add(new StudentResponse(student));
        }
        return studentResponses;
    }

    @GetMapping("/getByFirstNameAndLastName/{firstName}/{lastName}")
    public List<StudentResponse> getByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName) {
        List<Student> students = studentService.getByFirstNameAndLastName(firstName, lastName);
        List<StudentResponse> studentResponses = new ArrayList<StudentResponse>();
        for (Student student : students) {
            studentResponses.add(new StudentResponse(student));
        }
        return studentResponses;
    }

    @GetMapping("/getByFirstNameOrLastName/{firstName}/{lastName}")
    public List<StudentResponse> getByFirstNameOrLastName(@PathVariable String firstName, @PathVariable String lastName) {
        List<Student> students = studentService.getByFirstNameOrLastName(firstName, lastName);
        List<StudentResponse> studentResponses = new ArrayList<StudentResponse>();
        for (Student student : students) {
            studentResponses.add(new StudentResponse(student));
        }
        return studentResponses;
    }

    @GetMapping("/getByFirstNameIn")
    public List<StudentResponse> getByFirstNameIn(@RequestBody InQueryRequest inQueryRequest) {
        // logger.info("inQueryRequest: {}", inQueryRequest);
        List<Student> students = studentService.getByFirstNameIn(inQueryRequest.getFirstNames());
        List<StudentResponse> studentResponses = new ArrayList<StudentResponse>();
        for (Student student : students) {
            studentResponses.add(new StudentResponse(student));
        }
        logger.info("studentResponses: {}", studentResponses);
        return studentResponses;
    }

    @GetMapping("/getAllByPagination")
    public List<StudentResponse> getAllByPagination(@RequestParam int page, @RequestParam int size) {
        List<Student> students = studentService.getAllByPagination(page, size);
        List<StudentResponse> studentResponses = new ArrayList<StudentResponse>();
        for (Student student : students) {
            studentResponses.add(new StudentResponse(student));
        }
        return studentResponses;
    }

    @GetMapping("getAllBySorting")
    public List<StudentResponse> getAllBySorting(){
        List<Student> students = studentService.getAllBySorting();
        List<StudentResponse> studentResponses = new ArrayList<StudentResponse>();
        for (Student student : students) {
            studentResponses.add(new StudentResponse(student));
        }
        return studentResponses;
    }

    @GetMapping("getAllWithNameLike/{name}")
    public List<StudentResponse> getAllWithNameLike(@PathVariable String name){
        List<Student> students = studentService.getAllWithNameLike(name);
        List<StudentResponse> studentResponses = new ArrayList<StudentResponse>();
        for (Student student : students) {
            studentResponses.add(new StudentResponse(student));
        }
        return studentResponses;
    }

    @GetMapping("/getByFirstNameStartsWith/{firstName}")
    public List<StudentResponse> getByFirstNameStartsWith(@PathVariable String firstName){
        List<Student> students = studentService.getByFirstNameStartsWith(firstName);
        List<StudentResponse> studentResponses = new ArrayList<StudentResponse>();
        for (Student student : students) {
            studentResponses.add(new StudentResponse(student));
        }
        return studentResponses;
    }

    @PutMapping("/updateStudentFirstName/{id}/{firstName}")
    public String updateStudentFirstName(@PathVariable Long id, @PathVariable String firstName){
        return studentService.updateStudentFirstName(id, firstName)+" student(s) updated successfully";
    }

    @DeleteMapping("/deleteByFirstName/{firstName}")
    public String deleteByFirstName(@PathVariable String firstName){
        return studentService.deleteByFirstName(firstName)+" student(s) deleted successfully";
    }

    @GetMapping("getByCity/{city}")
    public List<StudentResponse> GetByCity(@PathVariable String city){
        List<Student> students = studentService.getByCity(city);
        List<StudentResponse> studentResponses = new ArrayList<StudentResponse>();
        for (Student student : students) {
            studentResponses.add(new StudentResponse(student));
        }
        return studentResponses;
    }
}
