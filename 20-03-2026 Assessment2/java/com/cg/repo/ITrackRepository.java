package com.cg.repo;


import com.cg.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ITrackRepository extends JpaRepository<Track, Long> {

    List<Track> findByTitle(String title);
}