package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.entity.Address;
import com.example.entity.Student;
import com.example.entity.Subject;
import com.example.request.CreateStudentRequest;
import com.example.request.CreateSubjectRequest;
import com.example.request.UpdateStudentRequest;
import com.example.repository.StudentRepository;
import com.example.repository.AdressRepository;
import com.example.repository.SubjectRepository;

@Service // This annotation tells Spring that this class is a service
public class StudentService {    

    @Autowired // This annotation tells Spring to inject the StudentRepository bean into the StudentService bean
    StudentRepository studentRepository;

    @Autowired
    AdressRepository adressRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(CreateStudentRequest createStudentRequest) {
        // 1. Create and save address
        Address address = new Address(createStudentRequest.getStreet(), createStudentRequest.getCity());
        address = adressRepository.save(address);
        
        // 2. Create and save student FIRST
        Student student = new Student(createStudentRequest);
        student.setAddress(address);
        student = studentRepository.save(student);  // Save student first!

        // 3. Now create and save subjects that reference the saved student
        List<Subject> subjects = new ArrayList<>();
        if (createStudentRequest.getSubjects() != null) {
            for (CreateSubjectRequest createSubjectRequest : createStudentRequest.getSubjects()) {
                Subject subject = new Subject();
                subject.setSubjectName(createSubjectRequest.getSubjectName());
                subject.setMarksObtained(createSubjectRequest.getMarksObtained());
                subject.setStudent(student);  // Now student is saved with ID
                subjects.add(subject);
            }
            subjects = subjectRepository.saveAll(subjects);  // This works now
            student.setSubjects(subjects);
        }
        
        return student;
    }

    public List<Student> createAllStudents(List<CreateStudentRequest> createStudentRequests) {
        List<Student> students = new ArrayList<>();
        for (CreateStudentRequest createStudentRequest : createStudentRequests) {
            // 1. Create and save address
            Address address = new Address(createStudentRequest.getStreet(), createStudentRequest.getCity());
            address = adressRepository.save(address);
            
            // 2. Create and save student FIRST
            Student student = new Student(createStudentRequest);
            student.setAddress(address);
            student = studentRepository.save(student);  // Save student first!

            // 3. Now create and save subjects
            List<Subject> subjects = new ArrayList<>();
            if (createStudentRequest.getSubjects() != null) {
                for (CreateSubjectRequest createSubjectRequest : createStudentRequest.getSubjects()) {
                    Subject subject = new Subject();
                    subject.setSubjectName(createSubjectRequest.getSubjectName());
                    subject.setMarksObtained(createSubjectRequest.getMarksObtained());
                    subject.setStudent(student);  // Reference saved student
                    subjects.add(subject);
                }
                subjects = subjectRepository.saveAll(subjects);
                student.setSubjects(subjects);
            }
            
            students.add(student);
        }
        return students;
    }

    public Student updateStudent(UpdateStudentRequest updateStudentRequest) {
        
        Student student = studentRepository.findById(updateStudentRequest.getId()).get();

        if(updateStudentRequest.getFirstName() != null && !updateStudentRequest.getFirstName().isEmpty()) {
            student.setFirstName(updateStudentRequest.getFirstName());
        }
        if(updateStudentRequest.getLastName() != null && !updateStudentRequest.getLastName().isEmpty()) {
            student.setLastName(updateStudentRequest.getLastName());
        }
        if(updateStudentRequest.getEmail() != null && !updateStudentRequest.getEmail().isEmpty()) {
            student.setEmail(updateStudentRequest.getEmail());
        }
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);
    }

    public List<Student> getByFirstNameAndLastName(String firstName, String lastName) {
        // return studentRepository.findByFirstNameAndLastName(firstName, lastName);
        return studentRepository.getByFirstNameAndLastName(firstName, lastName);
    }

    public List<Student> getByFirstNameOrLastName(String firstName, String lastName) {
        return studentRepository.findByFirstNameOrLastName(firstName, lastName);
    }

    public List<Student> getByFirstNameIn(List<String> firstNames) {
        return studentRepository.findByFirstNameIn(firstNames);
    }

    public List<Student> getAllByPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return studentRepository.findAll(pageable).getContent();
    }

    public List<Student> getAllBySorting() {
        Sort sort = Sort.by(Sort.Direction.ASC, "firstName", "lastName");
        return studentRepository.findAll(sort);
    }

    public List<Student> getAllWithNameLike(String name) {
        return studentRepository.findByFirstNameContainingOrLastNameContaining(name, name);
    }

    public List<Student> getByFirstNameStartsWith(String firstName) {
        return studentRepository.findByFirstNameStartsWith(firstName);
    }

    public Integer updateStudentFirstName(Long id, String firstName) {
        return studentRepository.updateStudentFirstName(id, firstName);
    }

    public Integer deleteByFirstName(String firstName) {
        return studentRepository.deleteByFirstName(firstName);
    }

    public List<Student> getByCity(String city){
        return studentRepository.findByAddressCity(city);
    }
}
