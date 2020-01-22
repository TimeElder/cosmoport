package com.space.controller;

import com.space.model.ShipDto;
import com.space.service.dao.ShipDAOImpl;
import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(ShipController.BASE_URL)
public class ShipController {

    public static final String BASE_URL = "/rest/ships";
    @Autowired
    private ShipDAOImpl shipDAO;


    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getAllShips(@RequestParam(value = "name", defaultValue = "") String name,
                                  @RequestParam(value = "planet", defaultValue = "") String planet,
                                  @RequestParam(value = "shipType", defaultValue = "") ShipType shipType,
                                  @RequestParam(value = "after", defaultValue = "") Long after,
                                  @RequestParam(value = "before", defaultValue = "") Long before,
                                  @RequestParam(value = "isUsed", defaultValue = "") Boolean isUsed,
                                  @RequestParam(value = "minSpeed", defaultValue = "") Double minSpeed,
                                  @RequestParam(value = "maxSpeed", defaultValue = "") Double maxSpeed,
                                  @RequestParam(value = "minCrewSize", defaultValue = "") Integer minCrewSize,
                                  @RequestParam(value = "maxCrewSize", defaultValue = "") Integer maxCrewSize,
                                  @RequestParam(value = "minRating", defaultValue = "") Double minRating,
                                  @RequestParam(value = "maxRating", defaultValue = "") Double maxRating,
                                  @RequestParam(value = "order", defaultValue = "ID") ShipOrder order,
                                  @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                                  @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize)
    {
       Page<Ship> page = shipDAO.findAllShips(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed,
               minCrewSize, maxCrewSize, minRating, maxRating, order, pageNumber, pageSize);
       return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
    }

    @GetMapping("/count")
    public Integer getShipCount(@RequestParam(value = "name", defaultValue = "") String name,
                      @RequestParam(value = "planet", defaultValue = "") String planet,
                      @RequestParam(value = "shipType", defaultValue = "") ShipType shipType,
                      @RequestParam(value = "after", defaultValue = "") Long after,
                      @RequestParam(value = "before", defaultValue = "") Long before,
                      @RequestParam(value = "isUsed", defaultValue = "") Boolean isUsed,
                      @RequestParam(value = "minSpeed", defaultValue = "") Double minSpeed,
                      @RequestParam(value = "maxSpeed", defaultValue = "") Double maxSpeed,
                      @RequestParam(value = "minCrewSize", defaultValue = "") Integer minCrewSize,
                      @RequestParam(value = "maxCrewSize", defaultValue = "") Integer maxCrewSize,
                      @RequestParam(value = "minRating", defaultValue = "") Double minRating,
                      @RequestParam(value = "maxRating", defaultValue = "") Double maxRating) {
        return shipDAO.getCount(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed,
                minCrewSize, maxCrewSize, minRating, maxRating);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getShipById(@PathVariable Long id) {
            return shipDAO.findById(id);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createShip(@RequestBody ShipDto shipParams) {
        return shipDAO.createShip(shipParams);

    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateShip(@PathVariable Long id, @RequestBody ShipDto shipParam) {
        return shipDAO.updateShip(id, shipParam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShip(@PathVariable Long id){
        if (id <= 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return shipDAO.deleteShip(id);

    }

}
