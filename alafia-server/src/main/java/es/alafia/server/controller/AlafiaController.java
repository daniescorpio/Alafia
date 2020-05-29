package es.alafia.server.controller;

import es.alafia.server.model.*;
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

    public List<DinnerTable> getDinnerTablesData() {
        return dataService.retrieveDinnerTablesData();
    }

    public List<Booking> getBookingsData() {
        return dataService.retrieveBookingsData();
    }

    public List<Client> getClientsData() {
        return dataService.retrieveClientsData();
    }

    public List<Order> getOrdersData() {
        return dataService.retrieveOrdersData();
    }

    public List<Course> getCoursesData() {
        return dataService.retrieveCoursesData();
    }

    public List<Drink> getDrinksData() {
        return dataService.retrieveDrinksData();
    }
}


