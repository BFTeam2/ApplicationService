package com.bfs.springdatademo.service;

import com.bfs.springdatademo.domain.Game;
import com.bfs.springdatademo.repository.MongoDemoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MongoDemoService {
    private final MongoDemoRepo repository;

//    @Autowired
    public MongoDemoService(MongoDemoRepo repository) {
        this.repository = repository;
    }

    public Game findGameByTitle(String title) {
//        return repository.findGameByTitle(title).get(0);
        return repository.findGameByTitleIgnoreCase(title).get(0);
    }

    public List<Game> findAllGames() {
        return repository.findAll();
    }

    public List<Game> findGamesByCategory(String category) {
        return repository.findGamesByCategory(category);
    }

    public void saveOrUpdateGame(Game game) {
        repository.save(game);
    }

    public List<Game> findGamesByAward(String name){
        return repository.findGamesByAwards_Name(name);
    }
}
