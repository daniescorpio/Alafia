package es.alafia.server.controller;

import es.alafia.server.model.*;
import es.alafia.server.model.exception.TableNotFoundException;
import es.alafia.server.service.DataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/dinner-tables")
    public List<DinnerTable> getDinnerTablesData() {
        return dataService.retrieveDinnerTablesData();
    }

    @GetMapping(value = "/bookings")
    public List<Booking> getBookingsData() {
        return dataService.retrieveBookingsData();
    }

    @GetMapping(value = "/clients")
    public List<Client> getClientsData() {
        return dataService.retrieveClientsData();
    }

    @GetMapping(value = "/orders")
    public List<Order> getOrdersData() {
        return dataService.retrieveOrdersData();
    }

    @GetMapping(value = "/courses")
    public List<Course> getCoursesData() {
        return dataService.retrieveCoursesData();
    }

    @GetMapping(value = "/drinks")
    public List<Drink> getDrinksData() {
        return dataService.retrieveDrinksData();
    }

    @GetMapping(value = "/active-table")
    public DinnerTable getActiveTable(String activeTableId) throws TableNotFoundException {
        return dataService.retrieveTable(activeTableId);
    }

    @PostMapping(value = "/restaurants")
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant saveNewRestaurant(@RequestBody Restaurant restaurant) {
        return dataService.saveNewRestaurant(restaurant);
    }

    @PostMapping(value = "/dinner-tables")
    @ResponseStatus(HttpStatus.CREATED)
    public DinnerTable saveNewDinnerTable(DinnerTable dinnerTable) {
        return dataService.saveNewDinnerTable(dinnerTable);
    }

    @PostMapping(value = "/bookings")
    @ResponseStatus(HttpStatus.CREATED)
    public Booking saveNewBooking(Booking booking) {
        return dataService.saveNewBooking(booking);
    }

    @PostMapping(value = "/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public Client saveNewClient(Client client) {
        return dataService.saveNewClient(client);
    }

    @PostMapping(value = "/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public Order saveNewOrder(Order order) {
        return dataService.saveNewOrder(order);
    }

    @PostMapping(value = "/courses")
    @ResponseStatus(HttpStatus.CREATED)
    public Course saveNewCourse(Course course) {
        return dataService.saveNewCourse(course);
    }

    @PostMapping(value = "/drinks")
    @ResponseStatus(HttpStatus.CREATED)
    public Drink saveNewDrink(Drink drink) {
        return dataService.saveNewDrink(drink);
    }
}


