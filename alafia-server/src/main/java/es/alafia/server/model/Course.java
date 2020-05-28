package es.alafia.server.model;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Course {

    @Id
    private String id;

    private String description;

    private Double price;

    private String nutritionalValues;

    private String obtainProcess;

    private String history;
}
