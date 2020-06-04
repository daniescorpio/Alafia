package es.alafia.server.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OldClientDTO extends ClientDTO{
    private String oldClientId;
}
