package es.alafia.server;

import es.alafia.server.model.*;
import es.alafia.server.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class LoadInitData {

    private final RestaurantRepository restaurantRepository;
    private final DinnerTableRepository dinnerTableRepository;
    private final BookingRepository bookingRepository;
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final CourseRepository courseRepository;
    private final DrinkRepository drinkRepository;

    public void retrieveData() {
        log.info("Drinks in DB: {}", drinkRepository.findAll().size());
        log.info("Courses in DB: {}", courseRepository.findAll().size());
        log.info("Orders in DB: {}", orderRepository.findAll().size());
        log.info("Clients in DB: {}", clientRepository.findAll().size());
        log.info("Bookings in DB: {}", bookingRepository.findAll().size());
        log.info("DinnerTables in DB: {}", dinnerTableRepository.findAll().size());
        log.info("Restaurant in DB {}", restaurantRepository.findAll().size());

        Drink drink = drinkRepository.findAll().get(0);
        log.info("{}: {}, {}", drink.getId(), drink.getDescription(), drink.getPrice());
    }

    public void loadData() {
        Drink drink_1 = Drink.builder()
                .description("CocaCola")
                .price(2.5)
                .build();
        Drink drink_2 = Drink.builder()
                .description("Sprite")
                .price(2.5)
                .build();
        Drink drink_3 = Drink.builder()
                .description("Water")
                .price(2.5)
                .build();
        Drink drink_4 = Drink.builder()
                .description("Wine")
                .price(2.5)
                .build();

        Course course_1 = Course.builder()
                .description("course 1")
                .history("history course 1")
                .nutritionalValues("nutritional values course 1")
                .obtainProcess("obtain process course 1")
                .price(6.9)
                .urlVideo("-Hi8uEbGk_4")
                .build();
        Course course_2 = Course.builder()
                .description("course 2")
                .history("history course 2")
                .nutritionalValues("nutritional values course 2")
                .obtainProcess("obtain process course 2")
                .price(7.9)
                .urlVideo("AEvclAvvMcI")
                .build();
        Course course_3 = Course.builder()
                .description("course 3")
                .history("history course 3")
                .nutritionalValues("nutritional values course 3")
                .obtainProcess("obtain process course 3")
                .price(8.9)
                .urlVideo("-Hi8uEbGk_4")
                .build();
        Course course_4 = Course.builder()
                .description("course 4")
                .history("history course 4")
                .nutritionalValues("nutritional values course 4")
                .obtainProcess("obtain process course 4")
                .price(9.9)
                .urlVideo("AEvclAvvMcI")
                .build();

        Order order_1 = Order.builder()
                .courses(List.of(course_1))
                .coursesIdServed(new ArrayList<>())
                .drinks(List.of(drink_1))
                .build();
        Order order_2 = Order.builder()
                .courses(List.of(course_1))
                .coursesIdServed(new ArrayList<>())
                .drinks(List.of(drink_1, drink_2, drink_3, drink_4))
                .build();
        Order order_3 = Order.builder()
                .courses(List.of(course_1, course_2, course_3, course_4))
                .coursesIdServed(new ArrayList<>())
                .drinks(List.of(drink_1))
                .build();
        Order order_4 = Order.builder()
                .courses(List.of())
                .coursesIdServed(new ArrayList<>())
                .drinks(List.of())
                .build();

        Client client_1 = Client.builder()
                .mail("mail client_1")
                .name("name client_1")
                .order(order_1)
                .build();
        Client client_2 = Client.builder()
                .mail("mail client_2")
                .name("name client_2")
                .order(order_2)
                .build();
        Client client_3 = Client.builder()
                .mail("mail client_3")
                .name("name client_3")
                .order(order_3)
                .build();
        Client client_4 = Client.builder()
                .mail("mail client_4")
                .name("name client_4")
                .order(order_4)
                .build();
        Client client_5 = Client.builder()
                .mail("mail client_5")
                .name("name client_5")
                .order(order_3)
                .build();
        Client client_6 = Client.builder()
                .mail("mail client_6")
                .name("name client_6")
                .order(order_4)
                .build();

        Booking booking_1 = Booking.builder()
                .client(client_1)
                .diners(List.of(client_1, client_2, client_3, client_4))
                .build();
        Booking booking_2 = Booking.builder()
                .client(client_4)
                .diners(List.of(client_5, client_6))
                .build();

        DinnerTable dinnerTable_1 = DinnerTable.builder()
                .booking(booking_1)
                .build();
        DinnerTable dinnerTable_2 = DinnerTable.builder()
                .booking(booking_2)
                .build();

        Restaurant restaurant = Restaurant.builder()
                .dinnerTables(List.of(dinnerTable_1, dinnerTable_2))
                .build();

        drinkRepository.save(drink_1);
        drinkRepository.save(drink_2);
        drinkRepository.save(drink_3);
        drinkRepository.save(drink_4);

        courseRepository.save(course_1);
        courseRepository.save(course_2);
        courseRepository.save(course_3);
        courseRepository.save(course_4);

        orderRepository.save(order_1);
        orderRepository.save(order_2);
        orderRepository.save(order_3);
        orderRepository.save(order_4);

        clientRepository.save(client_1);
        clientRepository.save(client_2);
        clientRepository.save(client_3);
        clientRepository.save(client_4);
        clientRepository.save(client_5);
        clientRepository.save(client_6);

        bookingRepository.save(booking_1);
        bookingRepository.save(booking_2);

        dinnerTableRepository.save(dinnerTable_1);
        dinnerTableRepository.save(dinnerTable_2);

        restaurantRepository.save(restaurant);
    }
}
