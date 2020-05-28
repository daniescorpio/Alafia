package es.alafia.server.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Drink {

    @Id
    private String id;

    private String description;

    private Double price;
}
