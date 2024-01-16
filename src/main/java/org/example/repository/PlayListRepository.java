package org.example.repository;

import org.example.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayListRepository extends JpaRepository<PlayList, Integer> {
}
