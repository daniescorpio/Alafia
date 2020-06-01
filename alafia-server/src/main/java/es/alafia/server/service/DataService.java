package es.alafia.server.service;

import es.alafia.server.model.*;
import es.alafia.server.model.dto.AddDrinkDTO;
import es.alafia.server.model.dto.ClientDTO;
import es.alafia.server.model.dto.UpdateCourseDTO;
import es.alafia.server.model.exception.RequestedItemNotFoundException;
import es.alafia.server.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class DataService {

    //TODO: Client of booking and this client on dinnerTable are not aligned

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

    public Client saveNewClient(ClientDTO client) throws RequestedItemNotFoundException {
        log.info("Saving new client for booking: {}", client.getBookingId());
        Order emptyOrder = Order.builder()
                .courses(List.of())
                .drinks(List.of())
                .build();
        Order orderSaved = orderRepository.save(emptyOrder);
        Client newClient = Client.builder()
                .name(client.getName())
                .mail(client.getMail())
                .order(orderSaved)
                .build();
        Client savedClient = clientRepository.save(newClient);
        updateParentsWithNewClientData(client, savedClient);
        return savedClient;
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

    public DinnerTable retrieveTable(String tableId) throws RequestedItemNotFoundException {
        try {
            return dinnerTableRepository.findById(tableId).orElseThrow();
        } catch (Exception e) {
            throw new RequestedItemNotFoundException("Table with id " + tableId + " not found in DB");
        }
    }

    public Client addDrinkInClient(AddDrinkDTO addDrinkDTO) throws RequestedItemNotFoundException {
        Client client;
        Order order;
        Drink drink;
        try {
            client = clientRepository.findById(addDrinkDTO.getClientId()).orElseThrow();
            log.info("Client with id {} retrieved from DB correctly", addDrinkDTO.getClientId());
        } catch (Exception e) {
            throw new RequestedItemNotFoundException("Client with id " + addDrinkDTO.getClientId() + " not found in DB");
        }
        try {
            order = orderRepository.findById(addDrinkDTO.getOrderId()).orElseThrow();
            log.info("Order with id {} retrieved from DB correctly", addDrinkDTO.getOrderId());
        } catch (Exception e) {
            throw new RequestedItemNotFoundException("Order with id " + addDrinkDTO.getOrderId() + " not found in DB");
        }
        try {
            drink = drinkRepository.findById(addDrinkDTO.getDrinkId()).orElseThrow();
            log.info("Drink with id {} retrieved from DB correctly", addDrinkDTO.getDrinkId());
        } catch (Exception e) {
            throw new RequestedItemNotFoundException("Drink with id " + addDrinkDTO.getDrinkId() + " not found in DB");
        }
        order.getDrinks().add(drink);
        orderRepository.save(order);

        client.getOrder().getDrinks().add(drink);
        Client clientWithDrinkAgreed = clientRepository.save(client);
        log.info("Drink agreed correctly in client");
        ClientDTO clientDTO = ClientDTO.builder()
                .bookingId(addDrinkDTO.getBookingId())
                .dinnerTableId(addDrinkDTO.getDinnerTableId())
                .restaurantId(addDrinkDTO.getRestaurantId())
                .build();

        updateParentsWithNewClientData(clientDTO, clientWithDrinkAgreed);

        return clientWithDrinkAgreed;
    }

    public Course updateCourseStatus(UpdateCourseDTO courseDTO) throws RequestedItemNotFoundException {
        Client client;
        Order order;
        Course course;
        try {
            client = clientRepository.findById(courseDTO.getClientId()).orElseThrow();
            log.info("Client with id {} retrieved from DB correctly", courseDTO.getClientId());
        } catch (Exception e) {
            throw new RequestedItemNotFoundException("Client with id " + courseDTO.getClientId() + " not found in DB");
        }
        try {
            order = orderRepository.findById(courseDTO.getOrderId()).orElseThrow();
            log.info("Order with id {} retrieved from DB correctly", courseDTO.getOrderId());
        }
        catch (Exception e) {
            throw new RequestedItemNotFoundException("Client with id " + courseDTO.getClientId() + " not found in DB");
        }
        try {
            course = courseRepository.findById(courseDTO.getCourseId()).orElseThrow();
            log.info("Course with id {} retrieved from DB correctly", courseDTO.getCourseId());
        } catch (Exception e) {
            throw new RequestedItemNotFoundException("Client with id " + courseDTO.getClientId() + " not found in DB");
        }
        boolean newCourseStatus = !course.isServed();
        course.setServed(newCourseStatus);
        courseRepository.save(course);
        log.info("New status for course with id {} is {}", courseDTO.getCourseId(), newCourseStatus);

        order.getCourses().stream().filter(c -> c.getId().equals(course.getId())).findFirst().get().setServed(course.isServed());
        orderRepository.save(order);

        client.setOrder(order);
        Client savedClient = clientRepository.save(client);
        log.info("Client with id {} updated with new course status", courseDTO.getClientId());
        ClientDTO clientDTO = ClientDTO.builder()
                .bookingId(courseDTO.getBookingId())
                .dinnerTableId(courseDTO.getDinnerTableId())
                .restaurantId(courseDTO.getRestaurantId())
                .build();
        updateParentsWithNewClientData(clientDTO, savedClient);
        return course;
    }

    private void updateParentsWithNewClientData(ClientDTO client, Client newClient) throws RequestedItemNotFoundException {
        log.info("Updating parents of client {}", newClient.getId());
        Booking booking;
        try {
            booking = bookingRepository.findById(client.getBookingId()).orElseThrow();
        } catch (Exception e) {
            throw new RequestedItemNotFoundException("Booking with id " + client.getBookingId() + " not found in DB");
        }
        Optional<Client> optionalClient = booking.getDiners().stream()
                .filter(diner -> diner.getId().equals(newClient.getId()))
                .findFirst();
        if (optionalClient.isPresent()) {
            //No add new client, just update
            optionalClient.get().setOrder(newClient.getOrder());
//            optionalClient.get().getOrder().setDrinks(newClient.getOrder().getDrinks());
        } else {
            booking.getDiners().add(newClient);
        }

        if (booking.getClient().getId().equals(newClient.getId())) {
            booking.setClient(newClient);
        }

        bookingRepository.save(booking);
        log.info("Booking updated with new data");

        DinnerTable dinnerTable;
        try {
            dinnerTable = dinnerTableRepository.findById(client.getDinnerTableId()).orElseThrow();
        } catch (Exception e) {
            throw new RequestedItemNotFoundException("Booking with id " + client.getBookingId() + " not found in DB");
        }
        dinnerTable.setBooking(booking);
        dinnerTableRepository.save(dinnerTable);
        log.info("Dinner table updated with new data");

        Restaurant restaurant;
        try {
            restaurant = restaurantRepository.findById(client.getRestaurantId()).orElseThrow();
        } catch (Exception e) {
            throw new RequestedItemNotFoundException("Booking with id " + client.getBookingId() + " not found in DB");
        }
        restaurant.getDinnerTables().stream().filter(table -> table.getId().equals(client.getDinnerTableId())).findFirst().orElseThrow().setBooking(booking);
        restaurantRepository.save(restaurant);
        log.info("Restaurant updated with new data");
    }

    public DinnerTable getDinersOfTable(String tableId) throws RequestedItemNotFoundException {
        try{
            return dinnerTableRepository.findById(tableId).orElseThrow();
        }
        catch (Exception e) {
            throw new RequestedItemNotFoundException("Dinner table with id " + tableId + " not found in DB");
        }
    }
}
