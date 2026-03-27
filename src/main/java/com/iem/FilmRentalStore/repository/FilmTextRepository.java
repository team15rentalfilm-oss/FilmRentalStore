package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.FilmText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmTextRepository extends JpaRepository<FilmText, Short>, JpaSpecificationExecutor<FilmText> {
}