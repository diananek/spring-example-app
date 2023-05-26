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

public class CategoryControllerTest {
    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAll_ReturnStatusCode200() throws Exception {
        when(this.categoryRepository.findAll()).thenReturn(List.of(
                new Category(1L, "Дом", false),
                new Category(2L, "Работа", true)
        ));

        this.mockMvc.perform(get("/categories"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$", hasSize(2)),
                        content().json("""
                                [
                                    {
                                        "id": 1,
                                        "name": "Дом",
                                        "isIncome": false
                                        
                                    },
                                    {
                                        "id": 2,
                                        "name": "Работа",
                                        "isIncome": true
                                    }
                                ]
                                """
                        )
                );

    }
}