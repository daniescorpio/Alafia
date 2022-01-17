package es.alafia.server.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    private String id;

    private String description;

    private Double price;

    private String nutritionalValues;

    private String obtainProcess;

    private String history;

    private String urlVideo;
}
