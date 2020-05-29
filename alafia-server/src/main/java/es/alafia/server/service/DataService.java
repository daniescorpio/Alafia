package es.alafia.server.service;

import es.alafia.server.model.*;
import es.alafia.server.model.exception.TableNotFoundException;
import es.alafia.server.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

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

    //TODO: Move under basic rest operations
    public DinnerTable retrieveTable(String tableId) throws TableNotFoundException {
        try {
            return dinnerTableRepository.findById(tableId).orElseThrow();
        }
        catch (Exception e) {
            throw new TableNotFoundException("Table with id " + tableId + " not found in DB");
        }
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
}
