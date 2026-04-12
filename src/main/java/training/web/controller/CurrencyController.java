package training.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import training.web.entity.Currency;
import training.web.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    // 取得所有幣別資料
    @GetMapping
    public List<Currency> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }

    // --- 取得單一幣別 ---
    @GetMapping("/{id}")
    public Currency getCurrency(@PathVariable String id) {
        return currencyService.getById(id);
    }

    // --- 新增幣別 ---
    @PostMapping
    public Currency createCurrency(@RequestBody Currency currency) {
        return currencyService.createCurrency(currency);
    }

    // --- 更新幣別 ---
    @PutMapping
    public Currency updateCurrency(@RequestBody Currency currency) {
        return currencyService.updateCurrency(currency);
    }

    // --- 刪除幣別 ---
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCurrency(@PathVariable String id) {
        currencyService.deleteCurrency(id);
        return ResponseEntity.ok("Deleted successfully: " + id);
    }
}