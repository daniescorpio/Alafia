package es.alafia.server.controller;

import es.alafia.server.model.DinnerTable;
import es.alafia.server.model.Restaurant;
import es.alafia.server.model.exception.TableNotFoundException;
import es.alafia.server.service.DataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

        assertNotNull(restaurantsRetrieved);
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
