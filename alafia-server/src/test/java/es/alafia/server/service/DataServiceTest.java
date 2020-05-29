package es.alafia.server.service;

import es.alafia.server.model.*;
import es.alafia.server.model.exception.TableNotFoundException;
import es.alafia.server.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    void shouldRetrieveFromDBATable() throws TableNotFoundException {
        String tableId = "tableId";
        when(dinnerTableRepository.findById(anyString())).thenReturn(Optional.of(DinnerTable.builder().build()));

        dataService.retrieveTable(tableId);

        verify(dinnerTableRepository).findById(tableId);
    }

    @Test
    void shouldRetrieveFromDBRestaurantsData() {
        when(restaurantRepository.findAll()).thenReturn(List.of(Restaurant.builder().build()));

        List<Restaurant> restaurantList = dataService.retrieveRestaurantsData();

        verify(restaurantRepository, times(1)).findAll();
        assertFalse(restaurantList.isEmpty());
    }

    @Test
    void shouldRetrieveFromDBDinnerTablesData() {
        when(dinnerTableRepository.findAll()).thenReturn(List.of(DinnerTable.builder().build()));

        List<DinnerTable> dinnerTableList = dataService.retrieveDinnerTablesData();

        verify(dinnerTableRepository, times(1)).findAll();
        assertFalse(dinnerTableList.isEmpty());
    }

    @Test
    void shouldRetrieveFromDBBookingsData() {
        when(bookingRepository.findAll()).thenReturn(List.of(Booking.builder().build()));

        List<Booking> bookingList = dataService.retrieveBookingsData();

        verify(bookingRepository, times(1)).findAll();
        assertFalse(bookingList.isEmpty());
    }

    @Test
    void shouldRetrieveFromDBClientsData() {
        when(clientRepository.findAll()).thenReturn(List.of(Client.builder().build()));

        List<Client> clientList = dataService.retrieveClientsData();

        verify(clientRepository, times(1)).findAll();
        assertFalse(clientList.isEmpty());
    }

    @Test
    void shouldRetrieveFromDBOrdersData() {
        when(orderRepository.findAll()).thenReturn(List.of(Order.builder().build()));

        List<Order> orderList = dataService.retrieveOrdersData();

        verify(orderRepository, times(1)).findAll();
        assertFalse(orderList.isEmpty());
    }

    @Test
    void shouldRetrieveFromDBCoursesData() {
        when(courseRepository.findAll()).thenReturn(List.of(Course.builder().build()));

        List<Course> courseList = dataService.retrieveCoursesData();

        verify(courseRepository, times(1)).findAll();
        assertFalse(courseList.isEmpty());
    }

    @Test
    void shouldRetrieveFromDBDrinksData() {
        when(drinkRepository.findAll()).thenReturn(List.of(Drink.builder().build()));

        List<Drink> drinkList = dataService.retrieveDrinksData();

        verify(drinkRepository, times(1)).findAll();
        assertFalse(drinkList.isEmpty());
    }

    @Test
    void shouldThrowTableNotFoundException_whenTableWithCommingIdIsNotSavedInDB() {
        String tableId = "tableId";
        when(dinnerTableRepository.findById(anyString())).thenReturn(Optional.empty());

        TableNotFoundException exception = assertThrows(TableNotFoundException.class, () -> {
            dataService.retrieveTable(tableId);
        });

        assertEquals("Table with id " + tableId + " not found in DB", exception.getMessage());
    }
}
