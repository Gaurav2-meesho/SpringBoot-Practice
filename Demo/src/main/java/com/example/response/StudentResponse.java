package com.example.response;

import java.util.List;
import java.util.stream.Collectors;

import com.example.entity.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

// import lombok.Getter;
// import lombok.Setter;
// import lombok.ToString;
// import lombok.EqualsAndHashCode;
import lombok.Data; 
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

// @Getter
// @Setter
// @ToString
// @EqualsAndHashCode

@Data   // same as @Getter, @Setter, @ToString, @EqualsAndHashCode combined
@AllArgsConstructor // constructor with all fields
@NoArgsConstructor // constructor with no fields
public class StudentResponse {
    
    @JsonIgnore // This will not be serialized to JSON
    private Long id;
    @JsonProperty("First Name") // This will be serialized to JSON with the key "first_name"
    private String firstName;
    @JsonProperty("Last Name") // This will be serialized to JSON with the key "last_name"
    private String lastName;
    @JsonProperty("Email") // This will be serialized to JSON with the key "email"
    private String email;
    @JsonProperty("Full Name") // This will be serialized to JSON with the key "full_name"
    private String fullName;
    @JsonProperty("Street") // This will be serialized to JSON with the key "street"
    private String street;
    @JsonProperty("City") // This will be serialized to JSON with the key "city"
    private String city;
    @JsonProperty("Subjects") // This will be serialized to JSON with the key "subjects"
    private List<SubjectResponse> subjects;
    public StudentResponse(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.email = student.getEmail();
        this.fullName = student.getFirstName() + " " + student.getLastName();
        this.street = student.getAddress().getStreet();
        this.city = student.getAddress().getCity();

        if(student.getSubjects() != null) {
            subjects = student.getSubjects().stream().map(SubjectResponse::new).collect(Collectors.toList());
        }
    }

}
