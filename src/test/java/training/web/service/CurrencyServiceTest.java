package training.web.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import training.web.dto.CurrencyRequestDto;
import training.web.dto.CurrencyResponseDto;
import training.web.entity.Currency;
import training.web.exception.EntityNotFoundException;
import training.web.mapper.CurrencyMapper;
import training.web.exception.DuplicateEntityException;
import training.web.repository.CurrencyRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @Mock
    private CurrencyMapper mapper;

    @InjectMocks
    private CurrencyService currencyService;

    // =========================
    // GET BY ID
    // =========================
    @Test
    void getById_shouldReturnDto() {

        Currency entity = new Currency();
        entity.setCode("USD");
        entity.setName("US Dollar");

        CurrencyResponseDto dto = new CurrencyResponseDto();
        dto.setCode("USD");
        dto.setName("US Dollar");

        when(currencyRepository.findById("USD"))
                .thenReturn(Optional.of(entity));

        when(mapper.toDto(entity))
                .thenReturn(dto);

        CurrencyResponseDto result = currencyService.getById("USD");

        assertEquals("USD", result.getCode());
    }

    @Test
    void getById_shouldThrowNotFound() {

        when(currencyRepository.findById("USD"))
                .thenReturn(Optional.empty());

        assertThrows(
                EntityNotFoundException.class,
                () -> currencyService.getById("USD")
        );
    }

    // =========================
    // CREATE
    // =========================
    @Test
    void createCurrency_shouldSave() {

        CurrencyRequestDto request = new CurrencyRequestDto();
        request.setCode("USD");
        request.setName("US Dollar");

        Currency entity = new Currency();
        entity.setCode("USD");
        entity.setName("US Dollar");

        Currency saved = new Currency();
        saved.setCode("USD");
        saved.setName("US Dollar");

        CurrencyResponseDto response = new CurrencyResponseDto();
        response.setCode("USD");
        response.setName("US Dollar");

        when(currencyRepository.existsById("USD")).thenReturn(false);
        when(mapper.toEntity(request)).thenReturn(entity);
        when(currencyRepository.save(entity)).thenReturn(saved);
        when(mapper.toDto(saved)).thenReturn(response);

        CurrencyResponseDto result = currencyService.createCurrency(request);

        assertEquals("USD", result.getCode());
    }

    @Test
    void createCurrency_shouldThrowConflict() {

        CurrencyRequestDto request = new CurrencyRequestDto();
        request.setCode("USD");

        when(currencyRepository.existsById("USD")).thenReturn(true);

        assertThrows(
                DuplicateEntityException.class,
                () -> currencyService.createCurrency(request)
        );
    }

    // =========================
    // UPDATE
    // =========================
    @Test
    void updateCurrency_shouldUpdate() {

        CurrencyRequestDto request = new CurrencyRequestDto();
        request.setCode("USD");
        request.setName("US Dollar");

        Currency entity = new Currency();
        entity.setCode("USD");

        Currency saved = new Currency();
        saved.setCode("USD");

        CurrencyResponseDto response = new CurrencyResponseDto();
        response.setCode("USD");

        when(currencyRepository.existsById("USD")).thenReturn(true);
        when(mapper.toEntity(request)).thenReturn(entity);
        when(currencyRepository.save(entity)).thenReturn(saved);
        when(mapper.toDto(saved)).thenReturn(response);

        CurrencyResponseDto result = currencyService.updateCurrency(request);

        assertEquals("USD", result.getCode());
    }

    @Test
    void updateCurrency_shouldThrowNotFound() {

        CurrencyRequestDto request = new CurrencyRequestDto();
        request.setCode("USD");

        when(currencyRepository.existsById("USD")).thenReturn(false);

        assertThrows(
                EntityNotFoundException.class,
                () -> currencyService.updateCurrency(request)
        );
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

        assertThrows(
                EntityNotFoundException.class,
                () -> currencyService.deleteCurrency("USD")
        );
    }
}