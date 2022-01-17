package es.alafia.server.controller;

import es.alafia.server.LoadInitData;
import es.alafia.server.model.*;
import es.alafia.server.model.dto.*;
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
    private final LoadInitData loadInitData;

    @GetMapping(value = "/load-data")
    public void loadMockedData() {
        if (dataService.checkDBEmpty()) {
            log.info("DB empty, loading initial data");
            loadInitData.loadData();
            log.info("Mocked data loaded in DB");
        } else {
            log.info("DB with data, load isn't necessary");
        }
    }

    @GetMapping(value = "/restaurants")
    public List<Restaurant> getRestaurantsData() {
        List<Restaurant> restaurants = dataService.retrieveRestaurantsData();
        log.info("Found {} restaurants in DB", restaurants.size());
        return restaurants;
    }

    @GetMapping(value = "/clients-confirmed/{tableId}")
    public boolean checkClientsConfirmedInTable(@PathVariable String tableId) {
        log.info("Checking status of clients for table {}", tableId);
        return dataService.checkClientsConfirmedInTable(tableId);
    }

    @GetMapping(value = "/dinner-tables")
    public List<DinnerTable> getDinnerTablesData() {
        List<DinnerTable> dinnerTables = dataService.retrieveDinnerTablesData();
        log.info("Found {} dinner tables in DB", dinnerTables.size());
        return dinnerTables;
    }

    @GetMapping(value = "/bookings")
    public List<Booking> getBookingsData() {
        List<Booking> bookings = dataService.retrieveBookingsData();
        log.info("Found {} bookings in DB", bookings.size());
        return bookings;
    }

    @GetMapping(value = "/clients")
    public List<Client> getClientsData() {
        List<Client> clients = dataService.retrieveClientsData();
        log.info("Found {} clients in DB", clients.size());
        return clients;
    }

    @GetMapping(value = "/clients/{clientId}")
    public Client getClientData(@PathVariable String clientId) {
        return dataService.retrieveClientData(clientId);
    }

    @GetMapping(value = "/orders")
    public List<Order> getOrdersData() {
        List<Order> orders = dataService.retrieveOrdersData();
        log.info("Found {} orders in DB", orders.size());
        return orders;
    }

    @GetMapping(value = "/courses")
    public List<Course> getCoursesData() {
        List<Course> courses = dataService.retrieveCoursesData();
        log.info("Found {} courses in DB", courses.size());
        return courses;
    }

    @GetMapping(value = "/courses/{courseId}")
    public CourseDTO getCourseUrl(@PathVariable String courseId) {
        CourseDTO course = dataService.retrieveCourseData(courseId);
        log.info("Found with id {} course in DB", courseId);
        log.info("Url of video: {}", course.getUrlVideo());
        return course;
    }

    @GetMapping(value = "/drinks")
    public List<Drink> getDrinksData() {
        List<Drink> drinks = dataService.retrieveDrinksData();
        log.info("Found {} drinks in DB", drinks.size());
        return drinks;
    }

    @GetMapping(value = "/active-table")
    public DinnerTable getActiveTable(String activeTableId) {
        return dataService.retrieveTable(activeTableId);
    }

    @GetMapping(value = "/diners/{tableId}")
    public DinnerTable getDinersOfTable(@PathVariable String tableId) {
        return dataService.getDinersOfTable(tableId);
    }


    @PostMapping(value = "/restaurants")
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant saveNewRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant newRestaurant = dataService.saveNewRestaurant(restaurant);
        log.info("Restaurant saved with id: {}", newRestaurant.getId());
        return newRestaurant;
    }

    @PostMapping(value = "/dinner-tables")
    @ResponseStatus(HttpStatus.CREATED)
    public DinnerTable saveNewDinnerTable(@RequestBody DinnerTable dinnerTable) {
        return dataService.saveNewDinnerTable(dinnerTable);
    }

    @PostMapping(value = "/bookings")
    @ResponseStatus(HttpStatus.CREATED)
    public Booking saveNewBooking(@RequestBody Booking booking) {
        return dataService.saveNewBooking(booking);
    }

    @PostMapping(value = "/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public Client replaceClient(@RequestBody OldClientDTO oldClient) {
        Client newClient = dataService.replaceClient(oldClient);
        log.info("Client saved with id: {} with new contact data", newClient.getId());
        return newClient;
    }

    @PostMapping(value = "/update-client")
    @ResponseStatus(HttpStatus.CREATED)
    public Client updateClientStatus(@RequestBody UpdateClientDTO client) {
        Client newClient = dataService.updateClientStatus(client);
        log.info("Client saved with id: {} to confirmed: {}", newClient.getId(), newClient.getConfirmed());
        return newClient;
    }

    @PostMapping(value = "/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public Order saveNewOrder(@RequestBody Order order) {
        return dataService.saveNewOrder(order);
    }

    @PostMapping(value = "/courses")
    @ResponseStatus(HttpStatus.CREATED)
    public Course saveNewCourse(@RequestBody Course course) {
        return dataService.saveNewCourse(course);
    }

    @PostMapping(value = "/drinks")
    @ResponseStatus(HttpStatus.CREATED)
    public Drink saveNewDrink(@RequestBody Drink drink) {
        return dataService.saveNewDrink(drink);
    }

    @PostMapping(value = "/add-drink")
    @ResponseStatus(HttpStatus.CREATED)
    public Client addDrinkInClient(@RequestBody AddDrinkDTO addDrinkDTO) {
        log.info("Trying to add drink with id {} in client with id {}", addDrinkDTO.getDrinkId(), addDrinkDTO.getClientId());
        return dataService.addDrinkInClient(addDrinkDTO);
    }

    @PostMapping(value = "/update-course")
    @ResponseStatus(HttpStatus.CREATED)
    public Order updateCourseInClient(@RequestBody UpdateCourseDTO courseDTO) {
        log.info("Trying to update course with id {} in client with id {}", courseDTO.getCourseId(), courseDTO.getClientId());
        return dataService.updateCourseStatus(courseDTO);
    }

    @GetMapping(value = "/table-bill/{activeTableId}")
    public TableBillDTO getTablleBill(@PathVariable String activeTableId) {
        return dataService.retrieveTableBill(activeTableId);
    }

    @PostMapping(value = "/migrationTest")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveClientAnswers(@RequestBody ClientAnswersDTO clientAnswersDTO) {
        log.info("Saving answers of migration test of client {}", clientAnswersDTO.getClientId());
        dataService.updateClientWithTestAnswers(clientAnswersDTO);
    }
}


