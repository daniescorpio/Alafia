package es.alafia.server.controller;

import es.alafia.server.model.*;
import es.alafia.server.model.exception.RequestedItemNotFoundException;
import es.alafia.server.service.DataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlafiaControllerTest {

    @Mock
    private DataService dataService;

    @InjectMocks
    private AlafiaController alafiaController;

    @Test
    void shouldCallDataServiceToRetrieveAllRestaurantsSavedInDB() {
        when(dataService.retrieveRestaurantsData()).thenReturn(List.of(Restaurant.builder().build()));

        List<Restaurant> restaurantsRetrieved = alafiaController.getRestaurantsData();

        verify(dataService, times(1)).retrieveRestaurantsData();
        assertFalse(restaurantsRetrieved.isEmpty());
    }

    @Test
    void shouldCallDataServiceToSaveNewRestaurantInDB() {
        Restaurant restaurant = Restaurant.builder().build();
        when(dataService.saveNewRestaurant(any(Restaurant.class))).thenReturn(restaurant);

        Restaurant response = alafiaController.saveNewRestaurant(restaurant);

        verify(dataService, times(1)).saveNewRestaurant(restaurant);
        assertEquals(restaurant, response);
    }

    @Test
    void shouldCallDataServiceToRetrieveAllDinnerTablesSavedInDB() {
        when(dataService.retrieveDinnerTablesData()).thenReturn(List.of(DinnerTable.builder().build()));

        List<DinnerTable> dinnerTablesData = alafiaController.getDinnerTablesData();

        verify(dataService, times(1)).retrieveDinnerTablesData();
        assertFalse(dinnerTablesData.isEmpty());
    }

    @Test
    void shouldCallDataServiceToSaveNewDinnerTableInDB() {
        DinnerTable dinnerTable = DinnerTable.builder().build();
        when(dataService.saveNewDinnerTable(any(DinnerTable.class))).thenReturn(dinnerTable);

        DinnerTable response = alafiaController.saveNewDinnerTable(dinnerTable);

        verify(dataService, times(1)).saveNewDinnerTable(dinnerTable);
        assertEquals(dinnerTable, response);
    }

    @Test
    void shouldCallDataServiceToRetrieveAllBookingsSavedInDB() {
        when(dataService.retrieveBookingsData()).thenReturn(List.of(Booking.builder().build()));

        List<Booking> bookingsData = alafiaController.getBookingsData();

        verify(dataService, times(1)).retrieveBookingsData();
        assertFalse(bookingsData.isEmpty());
    }

    @Test
    void shouldCallDataServiceToSaveNewBookingInDB() {
        Booking booking = Booking.builder().build();
        when(dataService.saveNewBooking(any(Booking.class))).thenReturn(booking);

        Booking response = alafiaController.saveNewBooking(booking);

        verify(dataService, times(1)).saveNewBooking(booking);
        assertEquals(booking, response);
    }

    @Test
    void shouldCallDataServiceToRetrieveAllClientsSavedInDB() {
        when(dataService.retrieveClientsData()).thenReturn(List.of(Client.builder().build()));

        List<Client> clientsData = alafiaController.getClientsData();

        verify(dataService, times(1)).retrieveClientsData();
        assertFalse(clientsData.isEmpty());
    }

    @Test
    void shouldCallDataServiceToSaveNewClientInDB() {
        Client client = Client.builder().build();
        when(dataService.saveNewClient(any(Client.class))).thenReturn(client);

        Client response = alafiaController.saveNewClient(client);

        verify(dataService, times(1)).saveNewClient(client);
        assertEquals(client, response);
    }

    @Test
    void shouldCallDataServiceToRetrieveAllOrdersSavedInDB() {
        when(dataService.retrieveOrdersData()).thenReturn(List.of(Order.builder().build()));

        List<Order> ordersData = alafiaController.getOrdersData();

        verify(dataService, times(1)).retrieveOrdersData();
        assertFalse(ordersData.isEmpty());
    }

    @Test
    void shouldCallDataServiceToSaveNewOrderInDB() {
        Order order = Order.builder().build();
        when(dataService.saveNewOrder(any(Order.class))).thenReturn(order);

        Order response = alafiaController.saveNewOrder(order);

        verify(dataService, times(1)).saveNewOrder(order);
        assertEquals(order, response);
    }

    @Test
    void shouldCallDataServiceToRetrieveAllCoursesSavedInDB() {
        when(dataService.retrieveCoursesData()).thenReturn(List.of(Course.builder().build()));

        List<Course> coursesData = alafiaController.getCoursesData();

        verify(dataService, times(1)).retrieveCoursesData();
        assertFalse(coursesData.isEmpty());
    }

    @Test
    void shouldCallDataServiceToSaveNewCourseInDB() {
        Course course = Course.builder().build();
        when(dataService.saveNewCourse(any(Course.class))).thenReturn(course);

        Course response = alafiaController.saveNewCourse(course);

        verify(dataService, times(1)).saveNewCourse(course);
        assertEquals(course, response);
    }

    @Test
    void shouldCallDataServiceToRetrieveAllDrinksSavedInDB() {
        when(dataService.retrieveDrinksData()).thenReturn(List.of(Drink.builder().build()));

        List<Drink> drinksData = alafiaController.getDrinksData();

        verify(dataService, times(1)).retrieveDrinksData();
        assertFalse(drinksData.isEmpty());
    }

    @Test
    void shouldCallDataServiceToSaveNewDrinkInDB() {
        Drink drink = Drink.builder().build();
        when(dataService.saveNewDrink(any(Drink.class))).thenReturn(drink);

        Drink response = alafiaController.saveNewDrink(drink);

        verify(dataService, times(1)).saveNewDrink(drink);
        assertEquals(drink, response);
    }

    @Test
    void shouldCallDataServiceToRetrieveDataOfActiveTable() throws RequestedItemNotFoundException {
        String activeTableId = "activeTableId";
        when(dataService.retrieveTable(anyString())).thenReturn(DinnerTable.builder().build());

        DinnerTable activeDinnerTable = alafiaController.getActiveTable(activeTableId);

        assertNotNull(activeDinnerTable);
        verify(dataService).retrieveTable(activeTableId);
    }
}
