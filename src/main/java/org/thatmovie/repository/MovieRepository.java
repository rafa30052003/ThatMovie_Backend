package org.thatmovie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thatmovie.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
