package es.alafia.server.controller;

import es.alafia.server.model.*;
import es.alafia.server.model.exception.TableNotFoundException;
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
    void shouldRetrieveAllRestaurantsSavedInDB() {
        when(dataService.retrieveRestaurantsData()).thenReturn(List.of(Restaurant.builder().build()));

        List<Restaurant> restaurantsRetrieved = alafiaController.getRestaurantsData();

        verify(dataService, times(1)).retrieveRestaurantsData();
        assertFalse(restaurantsRetrieved.isEmpty());
    }

    @Test
    void shouldRetrieveAllDinnerTablesSavedInDB() {
        when(dataService.retrieveDinnerTablesData()).thenReturn(List.of(DinnerTable.builder().build()));

        List<DinnerTable> dinnerTablesData = alafiaController.getDinnerTablesData();

        verify(dataService, times(1)).retrieveDinnerTablesData();
        assertFalse(dinnerTablesData.isEmpty());
    }

    @Test
    void shouldRetrieveAllBookingsSavedInDB() {
        when(dataService.retrieveBookingsData()).thenReturn(List.of(Booking.builder().build()));

        List<Booking> bookingsData = alafiaController.getBookingsData();

        verify(dataService, times(1)).retrieveBookingsData();
        assertFalse(bookingsData.isEmpty());
    }

    @Test
    void shouldRetrieveAllClientsSavedInDB() {
        when(dataService.retrieveClientsData()).thenReturn(List.of(Client.builder().build()));

        List<Client> clientsData = alafiaController.getClientsData();

        verify(dataService, times(1)).retrieveClientsData();
        assertFalse(clientsData.isEmpty());
    }

    @Test
    void shouldRetrieveAllOrdersSavedInDB() {
        when(dataService.retrieveOrdersData()).thenReturn(List.of(Order.builder().build()));

        List<Order> ordersData = alafiaController.getOrdersData();

        verify(dataService, times(1)).retrieveOrdersData();
        assertFalse(ordersData.isEmpty());
    }

    @Test
    void shouldRetrieveAllCoursesSavedInDB() {
        when(dataService.retrieveCoursesData()).thenReturn(List.of(Course.builder().build()));

        List<Course> coursesData = alafiaController.getCoursesData();

        verify(dataService, times(1)).retrieveCoursesData();
        assertFalse(coursesData.isEmpty());
    }

    @Test
    void shouldRetrieveAllDrinksSavedInDB() {
        when(dataService.retrieveDrinksData()).thenReturn(List.of(Drink.builder().build()));

        List<Drink> drinksData = alafiaController.getDrinksData();

        verify(dataService, times(1)).retrieveDrinksData();
        assertFalse(drinksData.isEmpty());
    }

    @Test
    void shouldRetrieveDataOfActiveTable() throws TableNotFoundException {
        String activeTableId = "activeTableId";
        when(dataService.retrieveTable(anyString())).thenReturn(DinnerTable.builder().build());

        DinnerTable activeDinnerTable = alafiaController.getActiveTable(activeTableId);

        assertNotNull(activeDinnerTable);
        verify(dataService).retrieveTable(activeTableId);
    }
}
