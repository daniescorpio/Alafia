package es.alafia.server.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTableDTO {
    private String dinnerTableId;
    private String restaurantId;
}
