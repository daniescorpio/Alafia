package es.alafia.server.service;

import es.alafia.server.model.*;
import es.alafia.server.model.dto.*;
import es.alafia.server.model.exception.RequestedItemNotFoundException;
import es.alafia.server.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
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
        var emptyOrder = Order.builder()
                .courses(List.of())
                .drinks(List.of())
                .build();
        var orderSaved = orderRepository.save(emptyOrder);
        var newClient = Client.builder()
                .name(client.getName())
                .mail(client.getMail())
                .order(orderSaved)
                .build();
        var savedClient = clientRepository.save(newClient);
        updateParentsWithNewClientData(client, savedClient);
        return savedClient;
    }

    public Client replaceClient(OldClientDTO oldClientDTO) {
        Client client;
        try {
            client = clientRepository.findById(oldClientDTO.getOldClientId()).orElseThrow();
        } catch (Exception e) {
            throw new RequestedItemNotFoundException("Client with id " + oldClientDTO.getOldClientId() + " not found in DB");
        }
        client.setConfirmed(true);
        client.setName(oldClientDTO.getName());
        client.setMail(oldClientDTO.getMail());
        clientRepository.save(client);
        updateParentsWithNewClientData(oldClientDTO, client);
        return client;
    }

    public Client updateClientStatus(UpdateClientDTO updateClientDTO) {
        Client client;
        try {
            client = clientRepository.findById(updateClientDTO.getOldClientId()).orElseThrow();
        } catch (Exception e) {
            throw new RequestedItemNotFoundException("Client with id " + updateClientDTO + " not found in DB");
        }
        client.setConfirmed(!client.getConfirmed());
        clientRepository.save(client);
        updateParentsWithNewClientData(updateClientDTO, client);
        return client;
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

    public DinnerTable retrieveTable(String tableId) {
        try {
            return dinnerTableRepository.findById(tableId).orElseThrow();
        } catch (Exception e) {
            throw new RequestedItemNotFoundException("Table with id " + tableId + " not found in DB");
        }
    }

    public Client addDrinkInClient(AddDrinkDTO addDrinkDTO) {
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
        var clientWithDrinkAgreed = clientRepository.save(client);
        log.info("Drink agreed correctly in client");
        var clientDTO = ClientDTO.builder()
                .name(client.getName())
                .mail(client.getMail())
                .bookingId(addDrinkDTO.getBookingId())
                .dinnerTableId(addDrinkDTO.getDinnerTableId())
                .restaurantId(addDrinkDTO.getRestaurantId())
                .build();

        updateParentsWithNewClientData(clientDTO, clientWithDrinkAgreed);

        return clientWithDrinkAgreed;
    }

    public Order updateCourseStatus(UpdateCourseDTO courseDTO) {
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
        } catch (Exception e) {
            throw new RequestedItemNotFoundException("Client with id " + courseDTO.getClientId() + " not found in DB");
        }
        try {
            course = courseRepository.findById(courseDTO.getCourseId()).orElseThrow();
            log.info("Course with id {} retrieved from DB correctly", courseDTO.getCourseId());
        } catch (Exception e) {
            throw new RequestedItemNotFoundException("Client with id " + courseDTO.getClientId() + " not found in DB");
        }

        var coursesServedInClient = client.getOrder().getCoursesIdServed();
        var courseIsServed = coursesServedInClient.stream().anyMatch(c -> c.equals(course.getId()));
        if (courseIsServed) {
            coursesServedInClient.remove(course.getId());
            log.info("Setting status of course {} to NOT SERVED", course.getId());
        } else {
            coursesServedInClient.add(course.getId());
            log.info("Setting status of course {} to SERVED", course.getId());
        }
        order.setCoursesIdServed(coursesServedInClient);
        client.getOrder().setCoursesIdServed(coursesServedInClient);

        orderRepository.save(order);

        client.setOrder(order);
        Client savedClient = clientRepository.save(client);
        log.info("Client with id {} updated with new course status", courseDTO.getClientId());
        var clientDTO = ClientDTO.builder()
                .mail(client.getMail())
                .name(client.getName())
                .bookingId(courseDTO.getBookingId())
                .dinnerTableId(courseDTO.getDinnerTableId())
                .restaurantId(courseDTO.getRestaurantId())
                .build();
        updateParentsWithNewClientData(clientDTO, savedClient);
        return order;
    }

    public DinnerTable getDinersOfTable(String tableId) {
        try {
            return dinnerTableRepository.findById(tableId).orElseThrow();
        } catch (Exception e) {
            throw new RequestedItemNotFoundException("Dinner table with id " + tableId + " not found in DB");
        }
    }

    private void updateParentsWithNewClientData(ClientDTO client, Client newClient) {
        log.info("Updating parents of client {}", newClient.getId());
        Booking booking;
        try {
            booking = bookingRepository.findById(client.getBookingId()).orElseThrow();
        } catch (Exception e) {
            throw new RequestedItemNotFoundException("Booking with id " + client.getBookingId() + " not found in DB");
        }
        var optionalClient = booking.getDiners().stream()
                .filter(diner -> diner.getId().equals(newClient.getId()))
                .findFirst();
        if (optionalClient.isPresent()) {
            optionalClient.get().setConfirmed(newClient.getConfirmed());
            optionalClient.get().setMail(client.getMail());
            optionalClient.get().setName(client.getName());
            optionalClient.get().setOrder(newClient.getOrder());
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

    public boolean checkClientsConfirmedInTable(String tableId) {
        DinnerTable table;
        try {
            table = dinnerTableRepository.findById(tableId).orElseThrow();
        } catch (Exception e) {
            throw new RequestedItemNotFoundException("DinnerTable with id " + tableId + " not found in DB");
        }
        return table.getBooking().getDiners().stream().allMatch(Client::getConfirmed);
    }

    public Client retrieveClientData(String clientId) {
        Client client;
        try {
            client = clientRepository.findById(clientId).orElseThrow();
        } catch (Exception e) {
            throw new RequestedItemNotFoundException("Client with id " + clientId + " not found in DB");
        }
        return client;
    }

    public TableBillDTO retrieveTableBill(String tableId) {
        try {
            final var diners = dinnerTableRepository.findById(tableId)
                    .map(DinnerTable::getBooking)
                    .map(Booking::getDiners)
                    .orElseThrow();
            return TableBillDTO.builder()
                    .courses(diners.stream()
                            .flatMap(dinner -> dinner.getOrder().getCourses().stream())
                            .collect(Collectors.toList()))
                    .drinks(diners.stream()
                            .flatMap(dinner -> dinner.getOrder().getDrinks().stream())
                            .collect(Collectors.toList()))
                    .build();
        } catch (Exception e) {
            throw new RequestedItemNotFoundException("Table with id " + tableId + " not found in DB");
        }
    }

    public void updateClientWithTestAnswers(ClientAnswersDTO clientAnswersDTO) {
        Client client;
        String clientId = clientAnswersDTO.getClientId();
        try {
            client = clientRepository.findById(clientId).orElseThrow();
        } catch (Exception e) {
            throw new RequestedItemNotFoundException("Client with id " + clientId + " not found in DB");
        }
        List<String> answers = client.getTestAnswers();
        if (answers == null) {
            answers = new ArrayList<>();
        }
        if (!answers.isEmpty()) {
            answers.clear();
        }
        answers.addAll(clientAnswersDTO.getAnswers());
        client.setTestAnswers(answers);
        log.info("Test answers saved for client {}", clientId);
        clientRepository.save(client);

        for (Client c : clientRepository.findAll()) {
            List<String> testAnswers = c.getTestAnswers();
            if (testAnswers != null) {
                log.info("Answers of client {}: 1: {}, 2: {}, 3: {}, 4: {}", c.getId(),
                        testAnswers.get(0),
                        testAnswers.get(1),
                        testAnswers.get(2),
                        testAnswers.get(3));
            }
        }
    }
}
