package sg.edu.nus.iss.Workshop16.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.Workshop16.model.Mastermind;
import sg.edu.nus.iss.Workshop16.service.BoardGameRedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(path = "/board-game", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class BoardGameController {
    private static final Logger logger = LoggerFactory.getLogger(BoardGameController.class);

    @Autowired
    BoardGameRedis service;

    @PostMapping
    public ResponseEntity<Mastermind> createBoardGame(@RequestBody Mastermind ms) {
        logger.info(" " + ms.getName());
        int x = service.save(ms);
        if (x > 0)
            ms.setInsertCount(x);
        return ResponseEntity.ok(ms);
    }

    @GetMapping(path = "/{msId}")
    public ResponseEntity<Mastermind> getGameBoardById(@PathVariable String msId) {
        Mastermind m = service.findById(msId);
        return ResponseEntity.ok(m);
    }

    @PutMapping(path = "/{msId}")
    public ResponseEntity<Mastermind> updateGameBoard(@RequestBody Mastermind ms) {
        int mResult = service.update(ms);
        if (mResult > 0)
            ms.setUpdateCount(mResult);
        return ResponseEntity.ok(ms);
    }
}
