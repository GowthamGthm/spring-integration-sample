package com.gthm.sample.utils;

import com.gthm.sample.model.Movies;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.random.RandomGenerator;

public class RandomData {

    static AtomicInteger integer = new AtomicInteger(0);

    public static List<Movies> getMovies(int LENGTH) {

        List<Movies> movieList = new ArrayList<>();

        for (int i = 0; i < LENGTH; i++) {

            Movies movie = new Movies();
            movie.setId(integer.getAndIncrement());
            movie.setMovieName(UUID.randomUUID().toString());
            movie.setYear(Long.valueOf(LocalDate.now().getYear() - i));

            movieList.add(movie);
        }
        return movieList;
    }



}