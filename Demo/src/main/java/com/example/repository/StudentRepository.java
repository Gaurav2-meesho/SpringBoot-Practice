package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Student;

@Repository // This annotation tells Spring that this interface is a repository
public interface StudentRepository extends JpaRepository<Student, Long>{    
    // This is the repository interface for the Student entity and in extends JpaRepository(entity type, primary key type)
    // JpaRepository is combination of CrudRepository and PagingAndSortingRepository
    // CrudRepository provides CRUD operations
    // PagingAndSortingRepository provides pagination and sorting operations
    // JpaRepository provides JPA specific operations
    // JpaRepository extends PagingAndSortingRepository
    // JpaRepository extends CrudRepository
    // JpaRepository extends Repository

    List<Student> findByFirstName(String firstName);
    List<Student> findByFirstNameAndLastName(String firstName, String lastName);
    List<Student> findByFirstNameOrLastName(String firstName, String lastName);
    List<Student> findByFirstNameIn(List<String> firstNames);
    List<Student> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
    List<Student> findByFirstNameStartsWith(String firstName);

    // @Query("FROM Student WHERE firstName = ?1 AND lastName = ?2")
    @Query("FROM Student WHERE firstName = :firstName AND lastName = :lastName")
    List<Student> getByFirstNameAndLastName(String firstName, String lastName);
    // @Query("FROM Student WHERE firstName = :firstname AND lastName = :lastname")
    // List<Student> getByFirstNameAndLastName2(@Param("firstname") String firstName, @Param("lastname") String lastName);

    @Modifying  //It return only two types of data 1. Integer(return the number of rows updated) 2. Void
    @Transactional
    @Query("UPDATE Student SET firstName = :firstName WHERE id = :id")
    Integer updateStudentFirstName( Long id, String firstName);

    @Modifying
    @Transactional
    @Query("DELETE FROM Student WHERE firstName = :firstName")
    Integer deleteByFirstName(String firstName);

    List<Student> findByAddressCity(String city);
}
