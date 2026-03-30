package com.iem.FilmRentalStore.dto;

public record FilmTextDTO(
        Short filmId,
        String title,
        String description
) {}