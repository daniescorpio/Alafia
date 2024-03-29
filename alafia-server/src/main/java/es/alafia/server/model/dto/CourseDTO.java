package es.alafia.server.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private String courseId;
    private String orderId;
    private String clientId;
    private String bookingId;
    private String dinnerTableId;
    private String restaurantId;
    private String urlVideo;
}
