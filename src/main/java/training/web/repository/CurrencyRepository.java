package training.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import training.web.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
    // 可自訂查詢方法，例如：
    // List<Currency> findByNameContaining(String keyword);
}