package es.alafia.server.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private String name;
    private String mail;
    private String bookingId;
    private String dinnerTableId;
    private String restaurantId;
    private Boolean confirmed;
    private List<String> testAnswers;
}
