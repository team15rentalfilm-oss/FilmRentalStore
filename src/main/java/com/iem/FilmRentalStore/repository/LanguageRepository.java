package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Integer> {

    Optional<Language> findByNameIgnoreCase(String name);

    List<Language> findByNameContainingIgnoreCase(String name);
}
