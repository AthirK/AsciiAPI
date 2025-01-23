package com.example.AsciiAPI.ascii;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AsciiRepository extends JpaRepository<Ascii, Integer>
{
    Optional<Ascii> findByTitle(String title);
}