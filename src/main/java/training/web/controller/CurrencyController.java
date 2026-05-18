package training.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import training.web.dto.ApiResponse;
import training.web.dto.CurrencyRequestDto;
import training.web.dto.CurrencyResponseDto;
import training.web.service.CurrencyService;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    // 取得所有幣別資料
    @GetMapping
    public ApiResponse<List<CurrencyResponseDto>> getAllCurrencies() {
        return ApiResponse.success(currencyService.getAllCurrencies());
    }

    // 取得單一幣別
    @GetMapping("/{id}")
    public ApiResponse<CurrencyResponseDto> getCurrency(@PathVariable String id) {
        return ApiResponse.success(currencyService.getById(id));
    }

    // 新增幣別
    @PostMapping
    public ApiResponse<CurrencyResponseDto> createCurrency(@RequestBody CurrencyRequestDto dto) {
        return ApiResponse.success(currencyService.createCurrency(dto));
    }

    // 更新幣別
    @PutMapping
    public ApiResponse<CurrencyResponseDto> updateCurrency(@RequestBody CurrencyRequestDto dto) {
        return ApiResponse.success(currencyService.updateCurrency(dto));
    }

    // 刪除幣別
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteCurrency(@PathVariable String id) {
        currencyService.deleteCurrency(id);
        return ApiResponse.success("Deleted successfully: " + id);
    }
}