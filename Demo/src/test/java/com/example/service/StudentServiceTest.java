package com.example.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.entity.Address;
import com.example.entity.Student;
import com.example.entity.Subject;
import com.example.service.StudentService;
import com.example.repository.StudentRepository;
import com.example.repository.AdressRepository;
import com.example.repository.SubjectRepository;
import com.example.request.CreateStudentRequest;
import com.example.request.CreateSubjectRequest;
import com.example.request.UpdateStudentRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    
    @Mock
    StudentRepository studentRepository;
    
    @Mock
    AdressRepository adressRepository;
    
    @Mock
    SubjectRepository subjectRepository;
    
    @InjectMocks
    StudentService studentService;

    @Test
    void testCreateStudent() {
        Address address = new Address("Aurangabad", "Haryana");
        address.setId(1L);
        
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john.doe@example.com");
        student.setAddress(address);
        student.setSubjects(new ArrayList<>());
        
        List<Subject> subjects = new ArrayList<>();
        Subject subject = new Subject();
        subject.setSubjectName("Math");
        subject.setMarksObtained(95.0);
        subject.setStudent(student);
        subjects.add(subject);
        
        Mockito.when(adressRepository.save(Mockito.any(Address.class))).thenReturn(address);
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student);
        Mockito.when(subjectRepository.saveAll(Mockito.any())).thenReturn(subjects);

        CreateStudentRequest request = new CreateStudentRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmail("john.doe@example.com");
        request.setStreet("Aurangabad");
        request.setCity("Haryana");
        request.setSubjects(new ArrayList<>());
        CreateSubjectRequest subjectRequest = new CreateSubjectRequest();
        subjectRequest.setSubjectName("Math");
        subjectRequest.setMarksObtained(95.0);
        request.getSubjects().add(subjectRequest);

        Student createdStudent = studentService.createStudent(request);
        Assertions.assertNotNull(createdStudent);
        System.out.println("createdStudent: " + createdStudent);
    }

    @Test
    void testCreateAllStudents() {
        Address address1 = new Address("Aurangabad1", "Haryana");
        Address address2 = new Address("Aurangabad2", "Haryana");
        address1.setId(1L);
        address2.setId(2L);
        
        Student student1 = new Student();
        student1.setId(1L);
        student1.setFirstName("John");
        student1.setLastName("Doe");
        student1.setEmail("john.doe@example.com");
        student1.setAddress(address1);
        student1.setSubjects(new ArrayList<>());

        Student student2 = new Student();
        student2.setId(2L);
        student2.setFirstName("Jane");
        student2.setLastName("Doe");
        student2.setEmail("jane.doe@example.com");
        student2.setAddress(address2);
        student2.setSubjects(new ArrayList<>());
        
        List<Subject> subjects1 = new ArrayList<>();
        Subject subject1 = new Subject();
        subject1.setSubjectName("Math");
        subject1.setMarksObtained(95.0);
        subject1.setStudent(student1);
        subjects1.add(subject1);

        List<Subject> subjects2 = new ArrayList<>();
        Subject subject2 = new Subject();
        subject2.setSubjectName("Science");
        subject2.setMarksObtained(90.0);
        subject2.setStudent(student2);
        subjects2.add(subject2);
        
        Mockito.lenient().when(adressRepository.save(Mockito.any(Address.class))).thenReturn(address1).thenReturn(address2);

        Mockito.lenient().when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student1).thenReturn(student2);
        
        Mockito.lenient().when(subjectRepository.saveAll(Mockito.any())).thenReturn(subjects1).thenReturn(subjects2);

        CreateStudentRequest request1 = new CreateStudentRequest();
        request1.setFirstName("John");
        request1.setLastName("Doe");
        request1.setEmail("john.doe@example.com");
        request1.setStreet("Aurangabad1");
        request1.setCity("Haryana");
        request1.setSubjects(new ArrayList<>());
        CreateSubjectRequest subjectRequest1 = new CreateSubjectRequest();
        subjectRequest1.setSubjectName("Math");
        subjectRequest1.setMarksObtained(95.0);
        request1.getSubjects().add(subjectRequest1);

        CreateStudentRequest request2 = new CreateStudentRequest();
        request2.setFirstName("Jane");
        request2.setLastName("Doe");
        request2.setEmail("jane.doe@example.com");
        request2.setStreet("Aurangabad2");
        request2.setCity("Haryana");
        request2.setSubjects(new ArrayList<>());
        CreateSubjectRequest subjectRequest2 = new CreateSubjectRequest();
        subjectRequest2.setSubjectName("Science");
        subjectRequest2.setMarksObtained(90.0);
        request2.getSubjects().add(subjectRequest2);

        List<Student> createdStudents = studentService.createAllStudents(Arrays.asList(request1, request2));
        Assertions.assertNotNull(createdStudents);
        System.out.println("createdStudents: " + createdStudents);
    }

    @Test
    void testUpdateStudent(){
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john.doe@example.com");
        student.setAddress(new Address("Aurangabad", "Haryana"));
        student.setSubjects(new ArrayList<>());
        
        Student updatedStudent = new Student();
        updatedStudent.setId(1L);
        updatedStudent.setFirstName("Jane");
        updatedStudent.setLastName("Doe");
        updatedStudent.setEmail("jane.doe@example.com");
        updatedStudent.setAddress(new Address("Aurangabad", "Haryana"));
        updatedStudent.setSubjects(new ArrayList<>());

        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(updatedStudent);

        UpdateStudentRequest request = new UpdateStudentRequest();
        request.setId(1L);
        request.setFirstName("Jane");


        Student responseStudent = studentService.updateStudent(request);
        Assertions.assertNotNull(responseStudent);
        Assertions.assertEquals(updatedStudent.getFirstName(), responseStudent.getFirstName());
        System.out.println("responseStudent: " + responseStudent);
    }

    @Test
    void testDeleteStudent(){
        Mockito.doNothing().when(studentRepository).deleteById(Mockito.anyLong());
        studentService.deleteStudent(1L);
        Mockito.verify(studentRepository, Mockito.times(1)).deleteById(1L);
        System.out.println("Student deleted successfully");

        Mockito.doThrow(new RuntimeException("Student not found")).when(studentRepository).deleteById(Mockito.anyLong());
        Assertions.assertThrows(RuntimeException.class, () -> studentService.deleteStudent(1L));
        System.out.println("Student not found");
    }
}
