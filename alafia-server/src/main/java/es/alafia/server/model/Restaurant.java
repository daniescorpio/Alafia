package es.alafia.server.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Restaurant {

    private List<DinnerTable> dinnerTables;
}
