package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
