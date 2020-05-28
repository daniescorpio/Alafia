package es.alafia.server.service;

import es.alafia.server.model.DinnerTable;
import es.alafia.server.model.exception.TableNotFoundException;
import es.alafia.server.repository.DinnerTableRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DataServiceTest {

    @Mock
    private DinnerTableRepository dinnerTableRepository;

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
    void shouldThrowTableNotFoundException_whenTableWithCommingIdIsNotSavedInDB() {
        String tableId = "tableId";
        when(dinnerTableRepository.findById(anyString())).thenReturn(Optional.empty());

        TableNotFoundException exception = assertThrows(TableNotFoundException.class, () -> {
            dataService.retrieveTable(tableId);
        });

        assertEquals("Table with id " + tableId + " not found in DB", exception.getMessage());
    }
}
