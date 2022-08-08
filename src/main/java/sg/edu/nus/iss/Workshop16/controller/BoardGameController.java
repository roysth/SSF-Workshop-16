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

//RestController returns a JSON file, Controller returns a html file
@RestController
//RequstMapping: map web requests to Spring Controller methods
//takes in JSON and gives out Json
@RequestMapping(path = "/board-game", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class BoardGameController {
    private static final Logger logger = LoggerFactory.getLogger(BoardGameController.class);

    //Autowired to use the different services & methods in BoardGameRedis
    @Autowired
    BoardGameRedis service;

    //ResponseEntity represents an HTTP response, including headers, body and status
    //Task 1: Saving data into redis
    //Post cus it creates a data to be saved in redis
    @PostMapping
    public ResponseEntity<Mastermind> createBoardGame(@RequestBody Mastermind ms) {
        logger.info(" " + ms.getName());
        int x = service.save(ms);
        if (x > 0)
            ms.setInsertCount(x);
        return ResponseEntity.ok(ms);
        //return 201 status (create successfully)
    }

    //Task 2: Retrieve data from redis
    @GetMapping(path = "/{msId}")
    public ResponseEntity<Mastermind> getGameBoardById(@PathVariable String msId) {
        Mastermind m = service.findById(msId);
        return ResponseEntity.ok(m);
    }

    //Task 3: Update data from redis
    @PutMapping(path = "/{msId}")
    public ResponseEntity<Mastermind> updateGameBoard(@RequestBody Mastermind ms) {
        int mResult = service.update(ms);
        if (mResult > 0)
            ms.setUpdateCount(mResult);
        return ResponseEntity.ok(ms);
            
    }
}

/*return ResponseEntity.ok(ms) 
 .ok will return the status code ()
 ms will return the payload
 It's a short form of:
 return ResponseEntity.status(200).body(ms)
*/
