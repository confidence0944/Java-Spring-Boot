package training.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import training.web.entity.Currency;
import training.web.repository.CurrencyRepository;

import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    public Currency getById(String id) {
        return currencyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Currency not found: %s".formatted(id)));
    }

    public Currency createCurrency(Currency currency) {
        if (currencyRepository.existsById(currency.getCode())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Currency already exists: %s".formatted(currency.getCode()));
        }
        return currencyRepository.save(currency);
    }

    public Currency updateCurrency(Currency currency) {
        if (!currencyRepository.existsById(currency.getCode())) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Currency not found: %s".formatted(currency.getCode()));
        }

        return currencyRepository.save(currency);
    }

    public void deleteCurrency(String id) {
        if (!currencyRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Currency not found: %s".formatted(id));
        }

        currencyRepository.deleteById(id);
    }
}