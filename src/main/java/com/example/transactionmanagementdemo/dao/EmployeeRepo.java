package com.bfs.springdatademo.repository;

import com.bfs.springdatademo.domain.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MongoDemoRepo extends MongoRepository<Game, String> {
    List<Game> findGameByTitle(String title);

    List<Game> findGamesByAwards_Name(String name);
    List<Game> findGameByTitleIgnoreCase(String title);

    @Query(value = "{category: ?0}")
    List<Game> findGamesByCategory(String category);

}