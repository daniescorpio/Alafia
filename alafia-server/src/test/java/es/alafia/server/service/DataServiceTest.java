package es.alafia.server.service;

import es.alafia.server.model.*;
import es.alafia.server.model.dto.ClientDTO;
import es.alafia.server.model.exception.RequestedItemNotFoundException;
import es.alafia.server.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DataServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private DinnerTableRepository dinnerTableRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private DrinkRepository drinkRepository;

    @InjectMocks
    private DataService dataService;

    @Test
    void shouldRetrieveFromDBRestaurantsData() {
        when(restaurantRepository.findAll()).thenReturn(List.of(Restaurant.builder().build()));

        var restaurantList = dataService.retrieveRestaurantsData();

        verify(restaurantRepository, times(1)).findAll();
        assertFalse(restaurantList.isEmpty());
    }

    @Test
    void shouldSaveInDBNewRestaurant() {
        var restaurant = Restaurant.builder().build();
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(restaurant);

        var restaurantSaved = dataService.saveNewRestaurant(restaurant);

        verify(restaurantRepository, times(1)).save(restaurant);
        assertEquals(restaurant, restaurantSaved);
    }

    @Test
    void shouldRetrieveFromDBDinnerTablesData() {
        when(dinnerTableRepository.findAll()).thenReturn(List.of(DinnerTable.builder().build()));

        var dinnerTableList = dataService.retrieveDinnerTablesData();

        verify(dinnerTableRepository, times(1)).findAll();
        assertFalse(dinnerTableList.isEmpty());
    }

    @Test
    void shouldSaveInDBNewDinnerTable() {
        var dinnerTable= DinnerTable.builder().build();
        when(dinnerTableRepository.save(any(DinnerTable.class))).thenReturn(dinnerTable);

        var dinnerTableSaved = dataService.saveNewDinnerTable(dinnerTable);

        verify(dinnerTableRepository, times(1)).save(dinnerTable);
        assertEquals(dinnerTable, dinnerTableSaved);
    }

    @Test
    void shouldRetrieveFromDBBookingsData() {
        when(bookingRepository.findAll()).thenReturn(List.of(Booking.builder().build()));

        var bookingList = dataService.retrieveBookingsData();

        verify(bookingRepository, times(1)).findAll();
        assertFalse(bookingList.isEmpty());
    }

    @Test
    void shouldSaveInDBNewBooking() {
        var booking = Booking.builder().build();
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        var bookingSaved = dataService.saveNewBooking(booking);

        verify(bookingRepository, times(1)).save(booking);
        assertEquals(booking, bookingSaved);
    }

    @Test
    void shouldRetrieveFromDBClientsData() {
        when(clientRepository.findAll()).thenReturn(List.of(Client.builder().build()));

        var clientList = dataService.retrieveClientsData();

        verify(clientRepository, times(1)).findAll();
        assertFalse(clientList.isEmpty());
    }

    @Test
    void shouldSaveInDBNewClient() throws RequestedItemNotFoundException {
        var client = Client.builder()
                .id("clientId")
                .build();
        var clientDTO = ClientDTO.builder()
                .bookingId("bookingId")
                .dinnerTableId("dinnerTableId")
                .restaurantId("restaurantId")
                .build();
        var booking = Booking.builder()
                .diners(List.of(client))
                .client(client)
                .build();
        var dinnerTable = DinnerTable.builder()
                .id("dinnerTableId")
                .build();
        var restaurant = Restaurant.builder()
                .dinnerTables(List.of(dinnerTable))
                .build();
        when(bookingRepository.findById(anyString())).thenReturn(Optional.of(booking));
        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(dinnerTableRepository.findById(anyString())).thenReturn(Optional.of(dinnerTable));
        when(restaurantRepository.findById(anyString())).thenReturn(Optional.of(restaurant));

        var clientSaved = dataService.saveNewClient(clientDTO);

        client.setId(null);
        verify(clientRepository, times(1)).save(client);
        assertEquals(client, clientSaved);
    }

    @Test
    void shouldRetrieveFromDBOrdersData() {
        when(orderRepository.findAll()).thenReturn(List.of(Order.builder().build()));

        var orderList = dataService.retrieveOrdersData();

        verify(orderRepository, times(1)).findAll();
        assertFalse(orderList.isEmpty());
    }

    @Test
    void shouldSaveInDBNewOrder() {
        var order = Order.builder().build();
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        var orderSaved = dataService.saveNewOrder(order);

        verify(orderRepository, times(1)).save(order);
        assertEquals(order, orderSaved);
    }

    @Test
    void shouldRetrieveFromDBCoursesData() {
        when(courseRepository.findAll()).thenReturn(List.of(Course.builder().build()));

        var courseList = dataService.retrieveCoursesData();

        verify(courseRepository, times(1)).findAll();
        assertFalse(courseList.isEmpty());
    }

    @Test
    void shouldSaveInDBNewCourse() {
        var course = Course.builder().build();
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        var courseSaved = dataService.saveNewCourse(course);

        verify(courseRepository, times(1)).save(course);
        assertEquals(course, courseSaved);
    }

    @Test
    void shouldRetrieveFromDBDrinksData() {
        when(drinkRepository.findAll()).thenReturn(List.of(Drink.builder().build()));

        var drinkList = dataService.retrieveDrinksData();

        verify(drinkRepository, times(1)).findAll();
        assertFalse(drinkList.isEmpty());
    }

    @Test
    void shouldSaveInDBNewDrink() {
        var drink = Drink.builder().build();
        when(drinkRepository.save(any(Drink.class))).thenReturn(drink);

        var drinkSaved = dataService.saveNewDrink(drink);

        verify(drinkRepository, times(1)).save(drink);
        assertEquals(drink, drinkSaved);
    }

    @Test
    void shouldRetrieveFromDBATable() throws RequestedItemNotFoundException {
        var tableId = "tableId";
        when(dinnerTableRepository.findById(anyString())).thenReturn(Optional.of(DinnerTable.builder().build()));

        dataService.retrieveTable(tableId);

        verify(dinnerTableRepository).findById(tableId);
    }

    @Test
    void shouldThrowTableNotFoundException_whenTableWithCommingIdIsNotSavedInDB() {
        var tableId = "tableId";
        when(dinnerTableRepository.findById(anyString())).thenReturn(Optional.empty());

        RequestedItemNotFoundException exception = assertThrows(RequestedItemNotFoundException.class, () -> {
            dataService.retrieveTable(tableId);
        });

        assertEquals("Table with id " + tableId + " not found in DB", exception.getMessage());
    }

    @Test
    void shouldReturnTrueWhenAllDinnersAreConfirmedInTable() {
        DinnerTable dinnerTable = DinnerTable.builder()
                .booking(Booking.builder()
                        .diners(List.of(
                                Client.builder().confirmed(true).build(),
                                Client.builder().confirmed(true).build())
                        )
                        .build())
                .build();
        when(dinnerTableRepository.findById(anyString())).thenReturn(Optional.of(dinnerTable));

        boolean clientsConfirmedInTable = dataService.checkClientsConfirmedInTable("tableId");

        assertTrue(clientsConfirmedInTable);
    }

    @Test
    void shouldReturnFalseWhenAnyDinnersAreNotConfirmedInTable() {
        DinnerTable dinnerTable = DinnerTable.builder()
                .booking(Booking.builder()
                        .diners(List.of(
                                Client.builder().confirmed(true).build(),
                                Client.builder().confirmed(false).build())
                        )
                        .build())
                .build();
        when(dinnerTableRepository.findById(anyString())).thenReturn(Optional.of(dinnerTable));

        boolean clientsConfirmedInTable = dataService.checkClientsConfirmedInTable("tableId");

        assertFalse(clientsConfirmedInTable);
    }

    @Test
    void shouldReturnFalseWhenAllDinnersAreNotConfirmedInTable() {
        DinnerTable dinnerTable = DinnerTable.builder()
                .booking(Booking.builder()
                        .diners(List.of(
                                Client.builder().confirmed(false).build(),
                                Client.builder().confirmed(false).build())
                        )
                        .build())
                .build();
        when(dinnerTableRepository.findById(anyString())).thenReturn(Optional.of(dinnerTable));

        boolean clientsConfirmedInTable = dataService.checkClientsConfirmedInTable("tableId");

        assertFalse(clientsConfirmedInTable);
    }
}
