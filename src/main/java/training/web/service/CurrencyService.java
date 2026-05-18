package training.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training.web.dto.CurrencyMessageDto;
import training.web.dto.CurrencyRequestDto;
import training.web.dto.CurrencyResponseDto;
import training.web.entity.Currency;
import training.web.exception.DuplicateEntityException;
import training.web.exception.EntityNotFoundException;
import training.web.mapper.CurrencyMapper;
import training.web.repository.CurrencyRepository;

import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CurrencyMapper mapper;

    public List<CurrencyResponseDto> getAllCurrencies() {
        return currencyRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public CurrencyResponseDto getById(String id) {
        Currency entity = currencyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Currency not found: " + id));

        return mapper.toDto(entity);
    }

    public CurrencyResponseDto createCurrency(CurrencyRequestDto dto) {
        if (currencyRepository.existsById(dto.getCode())) {
            throw new DuplicateEntityException("Currency already exists: " + dto.getCode());
        }
        Currency saved = currencyRepository.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    public CurrencyResponseDto updateCurrency(CurrencyRequestDto dto) {

        if (!currencyRepository.existsById(dto.getCode())) {
            throw new EntityNotFoundException("Currency not found: " + dto.getCode());
        }

        Currency saved = currencyRepository.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    public void deleteCurrency(String id) {
        if (!currencyRepository.existsById(id)) {
            throw new EntityNotFoundException("Currency not found: " + id);
        }

        currencyRepository.deleteById(id);
    }

    public void SaveCurrency(CurrencyMessageDto currency) {
        if (currencyRepository.existsById(currency.getCode())) {
            System.out.println("Currency Code is exist: " + currency.getCode());
            return;
        }
        Currency entity = new Currency();
        entity.setCode(currency.getCode());
        entity.setName(currency.getName());

        currencyRepository.save(entity);
    }
}