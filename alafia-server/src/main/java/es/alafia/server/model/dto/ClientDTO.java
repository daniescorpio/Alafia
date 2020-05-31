package es.alafia.server.model.dto;

import es.alafia.server.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
