# ☕ Spring Boot Web Practice

<p>
  <img src="https://img.shields.io/badge/Java-21-red?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-3.5.4-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
  <img src="https://img.shields.io/badge/Maven-Build-blue?style=for-the-badge&logo=apachemaven&logoColor=white"/>
</p>

---

## 📌 專案介紹

本專案為一個基於 **Spring Boot** 的入門練習專案，  
透過 RESTful API 實現完整的 CRUD 操作，並與資料庫整合。

主要學習目標：

* 熟悉 Spring Boot 專案架構  
* 建立 RESTful API (Create/Read/Update/Delete)  
* 與資料庫整合 (JPA / Hibernate)  
* Maven 專案管理

---

## 🚀 功能說明

* API 功能一覽：

| HTTP 方法 | 路徑 | 說明 |
|-----------|------|------|
| GET       | `/currencies` | 取得所有幣別資料 |
| GET       | `/currencies/{code}` | 取得指定幣別資料 |
| POST      | `/currencies` | 新增幣別資料 |
| PUT       | `/currencies` | 更新指定幣別資料 |
| DELETE    | `/currencies/{code}` | 刪除指定幣別資料 |

---

## 🛠️ 使用技術

* Java 21  
* Spring Boot 3.5.4  
* Maven  
* Spring Data JPA  
* Microsoft SQL Server

---

## 📂 專案結構

```text
src/
└── main/
    ├── java/
    │   └── training/
    │       └── web/
    │           ├── controller/
    │           │   └── CurrencyController.java
    │           ├── entity/
    │           │   └── Currency.java
    │           ├── repository/
    │           │   └── CurrencyRepository.java
    │           ├── Hello.java
    │           └── Point.java
    └── resources/
        ├── application.yml
        └── static/
            ├── imgs/
            └── test.html
```

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

## 🎯 未來可擴展

* 增加驗證與授權 (Spring Security)
* 測試套件整合 (JUnit)

---

## 📄 授權

本專案僅作為學習用途。