package org.thatmovie.repository;

import org.springframework.data.jpa.repository.Query;
import org.thatmovie.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    @Query("SELECT m FROM Member m WHERE m.username = ?1")
    Optional<Member> findByUsername(String username);


}
