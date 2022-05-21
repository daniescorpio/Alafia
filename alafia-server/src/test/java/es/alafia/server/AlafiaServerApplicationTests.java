package es.alafia.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.alafia.server.model.*;
import es.alafia.server.model.dto.OldClientDTO;
import es.alafia.server.repository.BookingRepository;
import es.alafia.server.repository.ClientRepository;
import es.alafia.server.repository.DinnerTableRepository;
import es.alafia.server.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableWebMvc
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AlafiaServerApplication.class)
class AlafiaServerApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private DinnerTableRepository dinnerTableRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void shouldGetRestaurantsData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/restaurants"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateNewRestaurant() throws Exception {
        var restaurant = Restaurant.builder().build();

        var mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(restaurant)))
                .andExpect(status().isCreated())
                .andReturn();

        assertNotNull(mapper.readValue(mvcResult.getResponse().getContentAsString(), Restaurant.class).getId());
    }

    @Test
    void shouldGetDinnerTablesData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/dinner-tables"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateNewDinnerTable() throws Exception {
        var dinnerTable = DinnerTable.builder().build();

        var mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/dinner-tables")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dinnerTable)))
                .andExpect(status().isCreated())
                .andReturn();

        assertNotNull(mapper.readValue(mvcResult.getResponse().getContentAsString(), DinnerTable.class).getId());
    }

    @Test
    void shouldGetBookingsData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/bookings"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateNewBooking() throws Exception {
        var booking = Booking.builder().build();

        var mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(booking)))
                .andExpect(status().isCreated())
                .andReturn();

        assertNotNull(mapper.readValue(mvcResult.getResponse().getContentAsString(), Booking.class).getId());
    }

    @Test
    void shouldGetClientsData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/clients"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateNewClient() throws Exception {
        var client = clientRepository.save(Client.builder()
                .id("ID")
                .build());
        var booking = bookingRepository.save(Booking.builder()
                .diners(new ArrayList<>())
                .client(client)
                .build());
        var dinnerTable = dinnerTableRepository.save(DinnerTable.builder().build());
        var restaurant = restaurantRepository.save(Restaurant.builder()
                .dinnerTables(List.of(dinnerTable))
                .build());

        final OldClientDTO oldClientDTO = new OldClientDTO("ID");
        oldClientDTO.setBookingId(booking.getId());
        oldClientDTO.setDinnerTableId(dinnerTable.getId());
        oldClientDTO.setRestaurantId(restaurant.getId());

        var mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(oldClientDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        assertNotNull(mapper.readValue(mvcResult.getResponse().getContentAsString(), Client.class).getId());
    }

    @Test
    void shouldGetOrdersData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateNewOrder() throws Exception {
        var order = Order.builder().build();

        var mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(order)))
                .andExpect(status().isCreated())
                .andReturn();

        assertNotNull(mapper.readValue(mvcResult.getResponse().getContentAsString(), Order.class).getId());
    }

    @Test
    void shouldGetCoursesData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/courses"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateNewCourse() throws Exception {
        var course = Course.builder().build();

        var mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(course)))
                .andExpect(status().isCreated())
                .andReturn();

        assertNotNull(mapper.readValue(mvcResult.getResponse().getContentAsString(), Course.class).getId());
    }

    @Test
    void shouldGetDrinksData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/drinks"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateNewDrink() throws Exception {
        var drink = Drink.builder().build();

        var mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/drinks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(drink)))
                .andExpect(status().isCreated())
                .andReturn();

        assertNotNull(mapper.readValue(mvcResult.getResponse().getContentAsString(), Drink.class).getId());
    }

}
