package training.web.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import training.web.entity.Currency;
import training.web.repository.CurrencyRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyService currencyService;

    @Test
    void getById_shouldReturnCurrency() {
        Currency currency = new Currency();
        currency.setCode("USD");
        currency.setName("US Dollar");

        when(currencyRepository.findById("USD"))
                .thenReturn(Optional.of(currency));

        Currency result = currencyService.getById("USD");

        assertEquals("USD", result.getCode());
    }

    @Test
    void getById_shouldThrowNotFound() {
        when(currencyRepository.findById("USD"))
                .thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(
                ResponseStatusException.class,
                () -> currencyService.getById("USD"));

        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    @Test
    void createCurrency_shouldSave() {
        Currency currency = new Currency();
        currency.setCode("USD");
        currency.setName("US Dollar");

        when(currencyRepository.existsById("USD")).thenReturn(false);
        when(currencyRepository.save(currency)).thenReturn(currency);

        Currency result = currencyService.createCurrency(currency);

        assertEquals("USD", result.getCode());
    }

    @Test
    void createCurrency_shouldThrowConflict() {
        Currency currency = new Currency();
        currency.setCode("USD");
        currency.setName("US Dollar");

        when(currencyRepository.existsById("USD")).thenReturn(true);

        ResponseStatusException ex = assertThrows(
                ResponseStatusException.class,
                () -> currencyService.createCurrency(currency));

        assertEquals(HttpStatus.CONFLICT, ex.getStatusCode());
    }

    // =========================
    // UPDATE
    // =========================

    @Test
    void updateCurrency_shouldUpdate() {
        Currency currency = new Currency();
        currency.setCode("USD");
        currency.setName("US Dollar");

        when(currencyRepository.existsById("USD")).thenReturn(true);
        when(currencyRepository.save(currency)).thenReturn(currency);

        Currency result = currencyService.updateCurrency(currency);

        assertEquals("USD", result.getCode());
    }

    @Test
    void updateCurrency_shouldThrowNotFound() {
        Currency currency = new Currency();
        currency.setCode("USD");
        currency.setName("US Dollar");

        when(currencyRepository.existsById("USD")).thenReturn(false);

        ResponseStatusException ex = assertThrows(
                ResponseStatusException.class,
                () -> currencyService.updateCurrency(currency));

        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    // =========================
    // DELETE
    // =========================

    @Test
    void deleteCurrency_shouldDelete() {
        when(currencyRepository.existsById("USD")).thenReturn(true);

        currencyService.deleteCurrency("USD");

        verify(currencyRepository, times(1)).deleteById("USD");
    }

    @Test
    void deleteCurrency_shouldThrowNotFound() {
        when(currencyRepository.existsById("USD")).thenReturn(false);

        ResponseStatusException ex = assertThrows(
                ResponseStatusException.class,
                () -> currencyService.deleteCurrency("USD"));

        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }
}