package es.alafia.server.service;

import es.alafia.server.model.*;
import es.alafia.server.model.exception.TableNotFoundException;
import es.alafia.server.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Restaurant> retrieveRestaurantsData() {
        return restaurantRepository.findAll();
    }

    public List<DinnerTable> retrieveDinnerTablesData() {
        return dinnerTableRepository.findAll();
    }

    public List<Booking> retrieveBookingsData() {
        return bookingRepository.findAll();
    }

    public List<Client> retrieveClientsData() {
        return clientRepository.findAll();
    }

    public List<Order> retrieveOrdersData() {
        return orderRepository.findAll();
    }

    public List<Course> retrieveCoursesData() {
        return courseRepository.findAll();
    }

    public List<Drink> retrieveDrinksData() {
        return drinkRepository.findAll();
    }

    public Restaurant saveNewRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public DinnerTable saveNewDinnerTable(DinnerTable dinnerTable) {
        return dinnerTableRepository.save(dinnerTable);
    }

    public Booking saveNewBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Client saveNewClient(Client client) {
        return clientRepository.save(client);
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
        }
        catch (Exception e) {
            throw new TableNotFoundException("Table with id " + tableId + " not found in DB");
        }
    }

}
