# ☕ Spring Boot Web Practice

<p>
  <img src="https://img.shields.io/badge/Java-21-red?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-3.5.4-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
  <img src="https://img.shields.io/badge/Maven-Build-blue?style=for-the-badge&logo=apachemaven&logoColor=white"/>
</p>

---

## 📌 專案介紹

本專案為一個基於 **Spring Boot** 建立的後端練習專案，  
透過 RESTful API 提供幣別（Currency）資料的 CRUD 操作，並整合資料庫與測試架構。

---

## 🚀 功能說明

| HTTP 方法 | 路徑 | 說明 |
|-----------|------|------|
| GET       | `/currencies` | 取得所有幣別資料 |
| GET       | `/currencies/{code}` | 取得指定幣別資料 |
| POST      | `/currencies` | 新增幣別資料 |
| PUT       | `/currencies` | 更新幣別資料 |
| DELETE    | `/currencies/{code}` | 刪除幣別資料 |

---

## 🛠️ 使用技術

* Java 21  
* Spring Boot 3.5.4  
* Spring Web  
* Spring Data JPA  
* Maven  
* Microsoft SQL Server  
* JUnit 5  

---

## 🧪 測試設計

本專案包含完整測試架構，確保程式品質與功能正確性。

### 🔹 單元測試（Unit Test）
針對 Service 層進行邏輯驗證，不依賴 Spring Boot 完整環境或資料庫。

- 測試商業邏輯正確性
- 使用 JUnit 5
- 執行快速、獨立

範例：
- `CurrencyServiceTest`

---

### 🔸 整合測試（Integration Test）
驗證完整系統流程，包含 Controller → Service → Repository。

- 啟動 Spring Boot Application Context
- 可搭配測試資料庫設定（application-test.yml）
- 驗證 API 行為與資料庫整合

範例：
- `CurrencyIntegrationTest`

---

## 📂 專案結構

```text
JAVA.SPRING.BOOT/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── training/web/
│   │   │       ├── controller/
│   │   │       │   └── CurrencyController.java
│   │   │       ├── entity/
│   │   │       │   └── Currency.java
│   │   │       ├── repository/
│   │   │       │   └── CurrencyRepository.java
│   │   │       ├── service/
│   │   │       │   ├── CurrencyService.java
│   │   │       │   └── Hello.java
│   │   └── resources/
│   │       └── application.yml
│   │
│   ├── test/
│   │   ├── java/
│   │   │   └── training/web/
│   │   │       ├── unit/
│   │   │       │   └── CurrencyServiceTest.java
│   │   │       └── integration/
│   │   │           └── CurrencyIntegrationTest.java
│   │   └── resources/
│   │       └── application-test.yml
│
│   └── static/
│       ├── imgs/
│       └── test.html
│
├── pom.xml
└── README.md
```
---

## 📖 學習重點

- **建立 RESTful API**  
  使用 `@RestController`、`@GetMapping`、`@PostMapping`、`@PutMapping`、`@DeleteMapping` 建立完整 CRUD API。  

- **與資料庫整合 (JPA / Hibernate)**  
  - 使用 `@Entity` 對應資料表  
  - 使用 `@Table` 與 `@Column` 設定表格名稱與欄位名稱  
  - Spring Data Repository 操作資料庫 (CrudRepository、findById、save、deleteById)  
  - Hibernate 自動處理 SQL 查詢與交易管理  

- **Maven 專案管理**  
  - 自動下載與管理專案依賴套件 (Spring Boot、SQL Server Driver 等)  
  - 管理專案版本與模組，方便團隊協作  
  - 提供編譯、測試、打包指令，如 `mvn clean install` 與 `mvn package`  

- **Spring Boot 與 SQL Server 連線設定**  
  - application.yml 設定資料庫連線、JPA 參數與 Hibernate 行為

---

## 📄 授權

本專案僅作為學習用途。