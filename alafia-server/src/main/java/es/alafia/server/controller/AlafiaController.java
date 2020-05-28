package es.alafia.server.controller;

import es.alafia.server.model.DinnerTable;
import es.alafia.server.model.Restaurant;
import es.alafia.server.model.exception.TableNotFoundException;
import es.alafia.server.service.DataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = "application/json;charset=UTF-8")
public class AlafiaController {

    private final DataService dataService;

    @GetMapping(value = "/restaurants")
    public List<Restaurant> getRestaurantsData() {
        return dataService.retrieveRestaurantsData();
    }

    @GetMapping(value = "/active-table")
    public DinnerTable getActiveTable(String activeTableId) throws TableNotFoundException {
        return dataService.retrieveTable(activeTableId);
    }
}


