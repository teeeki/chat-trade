package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Tmp;

public interface TmpRepository extends JpaRepository<Tmp, Integer> {

    Optional<Tmp> findById(Integer id);

    void deleteAllByName(String name);
}
