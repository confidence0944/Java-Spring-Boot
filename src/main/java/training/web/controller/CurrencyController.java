package training.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import training.web.entity.Currency;
import training.web.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyRepository currencyRepository;

    // 取得所有幣別資料
    @GetMapping
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    // --- 取得單一幣別 ---
    @GetMapping("/{code}")
    public ResponseEntity<Currency> getCurrencyByCode(@PathVariable String code) {
        Optional<Currency> currency = currencyRepository.findById(code);
        return currency.map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    // --- 新增幣別 ---
    @PostMapping
    public ResponseEntity<Currency> createCurrency(@RequestBody Currency currency) {
        if(currencyRepository.existsById(currency.getCode())) {
            return ResponseEntity.badRequest().build();
        }
        Currency saved = currencyRepository.save(currency);
        return ResponseEntity.ok(saved);
    }

    // --- 更新幣別 ---
    @PutMapping
    public ResponseEntity<Currency> updateCurrency(@RequestBody Currency currency) {
        if(!currencyRepository.existsById(currency.getCode())) {
            return ResponseEntity.notFound().build();
        }
        Currency updated = currencyRepository.save(currency);
        return ResponseEntity.ok(updated);
    }

    // --- 刪除幣別 ---
    @DeleteMapping("/{code}")
    public ResponseEntity<String> deleteCurrency(@PathVariable String code) {
        if(!currencyRepository.existsById(code)) {
            return ResponseEntity.notFound().build();
        }
        currencyRepository.deleteById(code);
        return ResponseEntity.ok(code);
    }
}