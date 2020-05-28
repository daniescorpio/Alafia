package es.alafia.server.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
public class Booking {

    @Id
    private String id;

    private Client client;

    private List<Client> diners;
}
