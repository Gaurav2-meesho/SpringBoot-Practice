package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import java.util.List;

import com.example.request.CreateStudentRequest;
import com.example.request.UpdateStudentRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity // This annotation tells JPA that this class is a JPA entity
@Table(name = "student") // This annotation tells JPA that this entity is mapped to the "students" table
public class Student {
    @Id // This annotation tells JPA that this field is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This annotation tells JPA that the primary key is generated automatically
    @Column(name = "id") // This annotation tells JPA that this field is a column in the "students" table
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Transient  //this is used to ignore the field from the database
    private String fullName;
    // @OneToOne   //default fetch type is EAGER
    @OneToOne(fetch = FetchType.LAZY)   //this is used to fetch the address from the database
    @JoinColumn(name = "address_id")    //this is the foreign key in the student table
    private Address address;

    @OneToMany(mappedBy = "student")
    private List<Subject> subjects;

    public Student(CreateStudentRequest createStudentRequest) {
        this.firstName = createStudentRequest.getFirstName();
        this.lastName = createStudentRequest.getLastName();
        this.email = createStudentRequest.getEmail();
        this.fullName = this.firstName + " " + this.lastName;
    }

    public Student(UpdateStudentRequest updateStudentRequest) {
        this.id = updateStudentRequest.getId();
        this.firstName = updateStudentRequest.getFirstName();
        this.lastName = updateStudentRequest.getLastName();
        this.email = updateStudentRequest.getEmail();
        this.fullName = this.firstName + " " + this.lastName;
    }
}
