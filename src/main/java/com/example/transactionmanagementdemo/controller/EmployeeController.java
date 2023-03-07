package com.example.transactionmanagementdemo.controller.employee;


import com.bfs.springdatademo.domain.Game;
import com.bfs.springdatademo.service.MongoDemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    private final MongoDemoService service;

    @Autowired
    public EmployeeController(MongoDemoService service) {
        this.service = service;
    }

    @GetMapping(value = "/all-games")
    @ApiOperation(value = "Find all games", response = Iterable.class)
    public List<Game> findAllGames() {
        return service.findAllGames();
    }

    @GetMapping(value = "/game/title/{title}")
    @ApiOperation(value = "Find game by title", response = Game.class)
    public Game findGameByTitle(@PathVariable String title) {
        return service.findGameByTitle(title);
    }

    @GetMapping(value = "/games/category/{category}")
    @ApiOperation(value = "Find games by category", response = Iterable.class)
    public List<Game> findGamesByCategory(@PathVariable String category) {
        return service.findGamesByCategory(category);
    }

    @GetMapping(value = "/games/award/{award}")
    @ApiOperation(value = "Find games by award name", response = Iterable.class)
    public List<Game> findGamesByAward(@PathVariable String award) {
        return service.findGamesByAward(award);
    }



    @PostMapping("/game")
    @ApiOperation(value = "Save or update game")
    public void saveOrUpdateGame(@RequestBody Game game) {
        service.saveOrUpdateGame(game);
    }
}
