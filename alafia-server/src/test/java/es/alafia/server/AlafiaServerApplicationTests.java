package es.alafia.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.alafia.server.model.Restaurant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableWebMvc
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AlafiaServerApplication.class)
class AlafiaServerApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LoadInitData loadInitData;

    @Test
    void contextLoads() throws Exception {
        loadInitData.loadData();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/restaurants"))
                .andExpect(status().isOk()).andReturn();

        List<Restaurant> list = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), List.class);
    }

}
