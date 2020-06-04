package es.alafia.server.controller;

import es.alafia.server.model.*;
import es.alafia.server.model.dto.ClientDTO;
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

        var restaurantsRetrieved = alafiaController.getRestaurantsData();

        verify(dataService, times(1)).retrieveRestaurantsData();
        assertFalse(restaurantsRetrieved.isEmpty());
    }

    @Test
    void shouldCallDataServiceToSaveNewRestaurantInDB() {
        var restaurant = Restaurant.builder().build();
        when(dataService.saveNewRestaurant(any(Restaurant.class))).thenReturn(restaurant);

        var response = alafiaController.saveNewRestaurant(restaurant);

        verify(dataService, times(1)).saveNewRestaurant(restaurant);
        assertEquals(restaurant, response);
    }

    @Test
    void shouldCallDataServiceToRetrieveAllDinnerTablesSavedInDB() {
        when(dataService.retrieveDinnerTablesData()).thenReturn(List.of(DinnerTable.builder().build()));

        var dinnerTablesData = alafiaController.getDinnerTablesData();

        verify(dataService, times(1)).retrieveDinnerTablesData();
        assertFalse(dinnerTablesData.isEmpty());
    }

    @Test
    void shouldCallDataServiceToSaveNewDinnerTableInDB() {
        var dinnerTable = DinnerTable.builder().build();
        when(dataService.saveNewDinnerTable(any(DinnerTable.class))).thenReturn(dinnerTable);

        var response = alafiaController.saveNewDinnerTable(dinnerTable);

        verify(dataService, times(1)).saveNewDinnerTable(dinnerTable);
        assertEquals(dinnerTable, response);
    }

    @Test
    void shouldCallDataServiceToRetrieveAllBookingsSavedInDB() {
        when(dataService.retrieveBookingsData()).thenReturn(List.of(Booking.builder().build()));

        var bookingsData = alafiaController.getBookingsData();

        verify(dataService, times(1)).retrieveBookingsData();
        assertFalse(bookingsData.isEmpty());
    }

    @Test
    void shouldCallDataServiceToSaveNewBookingInDB() {
        var booking = Booking.builder().build();
        when(dataService.saveNewBooking(any(Booking.class))).thenReturn(booking);

        var response = alafiaController.saveNewBooking(booking);

        verify(dataService, times(1)).saveNewBooking(booking);
        assertEquals(booking, response);
    }

    @Test
    void shouldCallDataServiceToRetrieveAllClientsSavedInDB() {
        when(dataService.retrieveClientsData()).thenReturn(List.of(Client.builder().build()));

        var clientsData = alafiaController.getClientsData();

        verify(dataService, times(1)).retrieveClientsData();
        assertFalse(clientsData.isEmpty());
    }

    @Test
    void shouldCallDataServiceToSaveNewClientInDB() throws RequestedItemNotFoundException {
        var client = Client.builder().build();
        var clientDto = ClientDTO.builder().build();
        when(dataService.saveNewClient(any(ClientDTO.class))).thenReturn(client);

        var response = alafiaController.replaceClient(clientDto);

        verify(dataService, times(1)).saveNewClient(clientDto);
        assertEquals(client, response);
    }

    @Test
    void shouldCallDataServiceToRetrieveAllOrdersSavedInDB() {
        when(dataService.retrieveOrdersData()).thenReturn(List.of(Order.builder().build()));

        var ordersData = alafiaController.getOrdersData();

        verify(dataService, times(1)).retrieveOrdersData();
        assertFalse(ordersData.isEmpty());
    }

    @Test
    void shouldCallDataServiceToSaveNewOrderInDB() {
        var order = Order.builder().build();
        when(dataService.saveNewOrder(any(Order.class))).thenReturn(order);

        var response = alafiaController.saveNewOrder(order);

        verify(dataService, times(1)).saveNewOrder(order);
        assertEquals(order, response);
    }

    @Test
    void shouldCallDataServiceToRetrieveAllCoursesSavedInDB() {
        when(dataService.retrieveCoursesData()).thenReturn(List.of(Course.builder().build()));

        var coursesData = alafiaController.getCoursesData();

        verify(dataService, times(1)).retrieveCoursesData();
        assertFalse(coursesData.isEmpty());
    }

    @Test
    void shouldCallDataServiceToSaveNewCourseInDB() {
        var course = Course.builder().build();
        when(dataService.saveNewCourse(any(Course.class))).thenReturn(course);

        var response = alafiaController.saveNewCourse(course);

        verify(dataService, times(1)).saveNewCourse(course);
        assertEquals(course, response);
    }

    @Test
    void shouldCallDataServiceToRetrieveAllDrinksSavedInDB() {
        when(dataService.retrieveDrinksData()).thenReturn(List.of(Drink.builder().build()));

        var drinksData = alafiaController.getDrinksData();

        verify(dataService, times(1)).retrieveDrinksData();
        assertFalse(drinksData.isEmpty());
    }

    @Test
    void shouldCallDataServiceToSaveNewDrinkInDB() {
        var drink = Drink.builder().build();
        when(dataService.saveNewDrink(any(Drink.class))).thenReturn(drink);

        var response = alafiaController.saveNewDrink(drink);

        verify(dataService, times(1)).saveNewDrink(drink);
        assertEquals(drink, response);
    }

    @Test
    void shouldCallDataServiceToRetrieveDataOfActiveTable() throws RequestedItemNotFoundException {
        var activeTableId = "activeTableId";
        when(dataService.retrieveTable(anyString())).thenReturn(DinnerTable.builder().build());

        var activeDinnerTable = alafiaController.getActiveTable(activeTableId);

        assertNotNull(activeDinnerTable);
        verify(dataService).retrieveTable(activeTableId);
    }
}
