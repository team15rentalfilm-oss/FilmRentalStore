package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
    List<Language> findByName(String name);
}