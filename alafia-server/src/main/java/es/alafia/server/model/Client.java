package es.alafia.server.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Client {

    @Id
    private String id;

    private String name;

    private String mail;

    private Order order;
}
