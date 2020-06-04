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
public class Client {

    @Id
    private String id;

    private String name;

    private String mail;

    private Order order;

    private Boolean confirmed = false;
}
