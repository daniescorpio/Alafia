package es.alafia.server.model.dto;

import es.alafia.server.model.Course;
import es.alafia.server.model.Drink;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableBillDTO {
    private List<Course> courses;
    private List<Drink> drinks;
}
