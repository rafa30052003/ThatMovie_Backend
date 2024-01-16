package org.thatmovie.repository;

import org.thatmovie.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayListRepository extends JpaRepository<PlayList, Integer> {
}
