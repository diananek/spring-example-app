package com.example.financeApp;

import com.example.financeApp.entity.Category;
import com.example.financeApp.entity.Goal;
import com.example.financeApp.entity.Transaction;
import com.example.financeApp.repository.CategoryRepository;
import com.example.financeApp.repository.GoalRepository;
import com.example.financeApp.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc

public class GoalControllerTest {
    @MockBean
    private GoalRepository goalRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAll_ReturnStatusCode200() throws Exception {
        when(this.goalRepository.findAll()).thenReturn(List.of(
                new Goal(1L, "Машина", 1000000.0, new Date(), new Date()),
                new Goal(2L, "Телефон", 20000.0, new Date(), new Date())
        ));

        this.mockMvc.perform(get("/goals"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$", hasSize(2)),
                        content().json("""
                                [
                                    {
                                        "id": 1,
                                        "amount": 1000000.0
                                    },
                                    {
                                        "id": 2,
                                        "amount": 20000.0
                                    }
                                ]
                                """
                        )
                );

    }
    @Test
    public void testCalculateDeposit_ReturnStatusCode200AndValidAmount() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = dateFormat.parse("20/05/2023");
        Date endDate = dateFormat.parse("14/05/2024");
        when(this.goalRepository.findById(1L)).thenReturn(
                Optional.of(new Goal(1L, "Машина", 360000.0,
                        startDate, endDate))
        );

        this.mockMvc.perform(get("/goals/calculate/1"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json("1000.0")
                );

    }
}