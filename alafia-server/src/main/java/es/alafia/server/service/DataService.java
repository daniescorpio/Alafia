package es.alafia.server.service;

import es.alafia.server.model.*;
import es.alafia.server.model.exception.TableNotFoundException;
import es.alafia.server.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class DataService {

    private final RestaurantRepository restaurantRepository;
    private final DinnerTableRepository dinnerTableRepository;
    private final BookingRepository bookingRepository;
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final CourseRepository courseRepository;
    private final DrinkRepository drinkRepository;

    public boolean checkDBEmpty() {
        return restaurantRepository.findAll().isEmpty();
    }

    public List<Restaurant> retrieveRestaurantsData() {
        log.info("Fetching restaurants...");
        return restaurantRepository.findAll();
    }

    public List<DinnerTable> retrieveDinnerTablesData() {
        log.info("Fetching dinner tables...");
        return dinnerTableRepository.findAll();
    }

    public List<Booking> retrieveBookingsData() {
        log.info("Fetching bookings...");
        return bookingRepository.findAll();
    }

    public List<Client> retrieveClientsData() {
        log.info("Fetching clients...");
        return clientRepository.findAll();
    }

    public List<Order> retrieveOrdersData() {
        log.info("Fetching orders...");
        return orderRepository.findAll();
    }

    public List<Course> retrieveCoursesData() {
        log.info("Fetching courses");
        return courseRepository.findAll();
    }

    public List<Drink> retrieveDrinksData() {
        log.info("Fetching drinks...");
        return drinkRepository.findAll();
    }

    public Restaurant saveNewRestaurant(Restaurant restaurant) {
        log.info("Saving new restaurant...");
        return restaurantRepository.save(restaurant);
    }

    public DinnerTable saveNewDinnerTable(DinnerTable dinnerTable) {
        return dinnerTableRepository.save(dinnerTable);
    }

    public Booking saveNewBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Client saveNewClient(ClientDTO client) {
        log.info("Saving new client for booking: {}", client.getBookingId());
        Client newClient = Client.builder()
                .name(client.getName())
                .mail(client.getMail())
                .build();
        updateBookingWithNewClientData(client, newClient);
        return clientRepository.save(newClient);
    }

    public Order saveNewOrder(Order order) {
        return orderRepository.save(order);
    }

    public Course saveNewCourse(Course course) {
        return courseRepository.save(course);
    }

    public Drink saveNewDrink(Drink drink) {
        return drinkRepository.save(drink);
    }

    //TODO: Move under basic rest operations

    public DinnerTable retrieveTable(String tableId) throws TableNotFoundException {
        try {
            return dinnerTableRepository.findById(tableId).orElseThrow();
        } catch (Exception e) {
            throw new TableNotFoundException("Table with id " + tableId + " not found in DB");
        }
    }

    private void updateBookingWithNewClientData(ClientDTO client, Client newClient) {
        Booking booking = bookingRepository.findById(client.getBookingId()).orElseThrow();
        booking.getDiners().add(
                newClient);
        bookingRepository.save(booking);
    }
}
