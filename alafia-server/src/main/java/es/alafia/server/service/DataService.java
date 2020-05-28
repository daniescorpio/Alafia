package es.alafia.server.service;

import es.alafia.server.model.DinnerTable;
import es.alafia.server.model.Restaurant;
import es.alafia.server.model.exception.TableNotFoundException;
import es.alafia.server.repository.DinnerTableRepository;
import es.alafia.server.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DataService {

    private final RestaurantRepository restaurantRepository;
    private final DinnerTableRepository dinnerTableRepository;

    public List<Restaurant> retrieveRestaurantsData() {
        return restaurantRepository.findAll();
    }

    public DinnerTable retrieveTable(String tableId) throws TableNotFoundException {
        try {
            return dinnerTableRepository.findById(tableId).orElseThrow();
        }
        catch (Exception e) {
            throw new TableNotFoundException("Table with id " + tableId + " not found in DB");
        }
    }


}
