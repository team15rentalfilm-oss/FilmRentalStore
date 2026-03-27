package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.FilmText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmTextRepository extends JpaRepository<FilmText, Integer>, JpaSpecificationExecutor<FilmText> {

    List<FilmText> findByTitleContainingIgnoreCase(String title);

    List<FilmText> findByDescriptionContainingIgnoreCase(String description);
}