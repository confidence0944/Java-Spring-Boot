package training.web.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.web.servlet.MockMvc;

import training.web.dto.CurrencyResponseDto;
import training.web.entity.Currency;
import training.web.repository.CurrencyRepository;
import org.junit.jupiter.api.BeforeEach;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class CurrencyApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    ObjectMapper mapper;

    List<Currency> currencies = new ArrayList<>();

    @BeforeEach
    void setup() {

        Currency currency1 = new Currency();
        currency1.setCode("USD");
        currency1.setName("美元");
        currencies.add(currency1);

        Currency currency2 = new Currency();
        currency2.setCode("EUR");
        currency2.setName("歐元");
        currencies.add(currency2);

        Currency currency3 = new Currency();
        currency3.setCode("GBP");
        currency3.setName("英鎊");
        currencies.add(currency3);

        currencyRepository.saveAll(currencies);
    }

    @Test
    void get_currency_via_api() throws Exception {

        var result = mockMvc.perform(get("/currencies"))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        List<CurrencyResponseDto> datas = mapper.readValue(json, new TypeReference<List<CurrencyResponseDto>>() {
        });

        assertEquals(3, datas.size());

        for (Currency expected : currencies) {

            assertTrue(datas.stream().anyMatch(c -> expected.getCode().equals(c.getCode())
                    && expected.getName().equals(c.getName())));
        }
    }

    @Test
    void create_and_get_currency_via_api() throws Exception {

        String json = """
                {
                    "code": "JPN",
                    "name": "日幣"
                }
                """;

        mockMvc.perform(post("/currencies")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk());

        Currency saved = currencyRepository.findById("JPN").orElse(null);

        assertNotNull(saved);
        assertEquals("日幣", saved.getName());
    }

    @Test
    void get_currency_by_id_should_return_404() throws Exception {
        mockMvc.perform(get("/currencies/INVALID"))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_duplicate_currency_should_return_conflict() throws Exception {
        String json = """
                {
                    "code": "USD",
                    "name": "US Dollar"
                }
                """;

        mockMvc.perform(post("/currencies")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isConflict());
    }
}