package com.example.financeApp;

import com.example.financeApp.entity.Category;
import com.example.financeApp.entity.Transaction;
import com.example.financeApp.repository.CategoryRepository;
import com.example.financeApp.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc

public class TransactionControllerTest {
    @MockBean
    private TransactionRepository repository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testFindByCategoryId_ReturnStatusCode200() throws Exception {
        given(this.repository.findByCategoryId(any())).willReturn(List.of(new Transaction(42L,400.0,
                false, new Date(), " ",new Category(21L, "Продукты", false))));
        this.mockMvc.perform(get("/transactions/category/21"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    @Test
    public void testGetAll_ReturnStatusCode200() throws Exception {
        when(this.repository.findAll()).thenReturn(List.of(
                new Transaction(1L, 400.0,
                                false, new Date(), " ",
                                new Category(1L, "Продукты", false)),
                new Transaction(2L, 100.0,
                        false, new Date(), " ",
                        new Category(1L, "Продукты", false))
        ));

        this.mockMvc.perform(get("/transactions"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$", hasSize(2)),
                        content().json("""
                                [
                                    {
                                        "id": 1,
                                        "amount": 400.0,
                                        "isIncome": false,
                                        "comment": " ",
                                        "category":
                                            {"id":1,
                                            "name":"Продукты",
                                            "isIncome":false}
                                        
                                    },
                                    {
                                        "id": 2,
                                        "amount": 100.0,
                                        "isIncome": false,
                                        "comment": " ",
                                        "category":
                                            {"id":1,
                                            "name":"Продукты",
                                            "isIncome":false}
                                    }
                                ]
                                """
                        )
                );

    }
}