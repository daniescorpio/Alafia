package es.alafia.server.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
public class Order {

    @Id
    private String id;

    private List<Course> courses;

    private List<Drink> drinks;
}
