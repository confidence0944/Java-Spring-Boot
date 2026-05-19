# ☕ Spring Boot Web Practice

<p>
  <img src="https://img.shields.io/badge/Java-21-red?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-3.5.4-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
  <img src="https://img.shields.io/badge/Maven-Build-blue?style=for-the-badge&logo=apachemaven&logoColor=white"/>
  <img src="https://img.shields.io/badge/RabbitMQ-MQTT-orange?style=for-the-badge&logo=rabbitmq&logoColor=white"/>
</p>

---

## 📌 專案介紹

本專案為基於 Spring Boot 建立的後端練習專案，  
提供 Currency（幣別）資料的 RESTful CRUD API，  
並整合資料庫、MQTT 訊息處理、排程任務與完整測試架構。

專案採用典型分層式架構（Layered Architecture），
將 Controller、Service、Repository、DTO 與 Exception Handling 分離，
提升系統可維護性、擴展性與測試性。

### 🔹 Core Features

- RESTful API CRUD
- Microsoft SQL Server 整合
- Spring Data JPA / Hibernate
- MQTT 非同步訊息處理
- RabbitMQ MQTT Broker
- Scheduler Background Jobs
- DTO Layer Separation  (DTO 分層設計)
- Unified ApiResponse Format (統一Api回傳格式)
- Global Request Body Validation (全域Request Body驗證)
- Global Exception Handling  (全域Exception 處理機制)
- Unit Test & Integration Test

### 🔹 Technical Highlights

- 使用 DTO 隔離 Entity 與 API 資料結構
- 使用 `@RestControllerAdvice` 統一例外處理
- 採用 Event-Driven Messaging 處理 MQTT 訊息
- Scheduler 自動執行背景同步任務
- Integration Test 驗證完整 API Flow
- 使用 Maven 管理依賴與建置流程


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

## 🔸 MQTT 功能

- MQTT Publisher 發送訊息
- MQTT Subscriber 訂閱 Topic
- RabbitMQ MQTT Plugin 作為 MQTT Broker

## 🔹 排程功能（Scheduler）

使用 Spring Scheduler 定時執行背景任務：

- 自動發送 MQTT 訊息
- 定時同步資料
- 背景批次處理

範例：

```text
CurrencyJob.java
```


--- 
## 🧾 DTO 分層設計（Data Transfer Object）

為了避免 Entity 直接暴露於 API，系統採用 DTO 分層：

🎯 設計目的
- 避免資料庫結構直接暴露，提升 API 安全性
- 清楚區分 Request / Response
- 支援未來 API 擴展


--- 
## 📡 統一 API 回應格式（ApiResponse）

系統所有 API 回應統一使用 ApiResponse<T> 格式。

🎯 設計目的
- 統一前後端溝通格式
- 提升 API 可讀性與一致性
- 支援錯誤碼與訊息管理
- 方便前端處理狀態

```json
{
  "success": true,
  "message": "SUCCESS",
  "code": 200,
  "data": {}
}
```

範例：
- `ApiResponse.java`


--- 
## ⚠️ 全域例外處理（Global Exception Handler）

系統導入 @RestControllerAdvice 統一處理錯誤。

🎯 設計目的
- 集中管理 Exception
- 統一錯誤回應格式
- 提升維護性

範例：
- `GlobalExceptionHandler.java`

---

## 🔐 全域請求體驗證（Global Request Body Validation）

系統導入 `RequestBodyValidationAdvice` 統一驗證所有 `@RequestBody` 參數，類似 ASP.NET ActionFilter 機制。

🎯 設計目的
- 集中管理請求體驗證邏輯
- 減少 Controller 重複驗證代碼
- 統一驗證錯誤回應格式
- 提升代碼可維護性

### 📋 驗證流程

1. **請求進入**：API 接收 `@RequestBody` 參數
2. **RequestBodyValidationAdvice 攔截**：所有 @RequestBody 參數自動進入驗證層
3. **Bean Validation 檢查**：根據 DTO 上的驗證註解（如 `@NotBlank`）驗證
4. **錯誤處理**：若驗證失敗，拋出 `ConstraintViolationException`
5. **全域例外處理**：`GlobalExceptionHandler` 捕捉錯誤，回傳統一格式的 `ApiResponse.error(...)`

**3. 驗證失敗回應範例：**

```json
{
  "success": false,
  "message": "參數驗證失敗 - code: code is required",
  "code": 422,
  "data": null
}
```

---

## 🛠️ 使用技術

- Java 21
- Spring Boot 3.5.4
- Spring Web
- Spring Data JPA
- Spring Scheduler
- MQTT
- RabbitMQ
- Maven
- Microsoft SQL Server
- JUnit 5 

---

## 🏗️ 系統架構

```text
Client
   ↓
REST API (Controller)
   ↓
Service Layer
   ↓
Repository (JPA)
   ↓
SQL Server

MQTT Publisher
   ↓
RabbitMQ MQTT Broker
   ↓
MQTT Subscriber

Scheduler Job
   ↓
自動發送 MQTT 訊息 / 同步資料
```

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
├─ src/
│  ├─ main/
│  │  ├─ java/
│  │  │  └─ training/web/
│  │  │      ├─ config/           # MQTT Pub/Sub Component
│  │  │      ├─ controller/       # REST API Controller
│  │  │      ├─ dto/              # Request / Response DTO
│  │  │      ├─ entity/           # JPA Entity
│  │  │      ├─ enums/            # 系統列舉與狀態碼
│  │  │      ├─ exception/        # 全域例外處理
│  │  │      ├─ job/              # Scheduler 排程任務
│  │  │      ├─ mapper/           # Entity 與 DTO 轉換
│  │  │      ├─ mqtt/             # MQTT Consumer Handler
│  │  │      ├─ repository/       # JPA Repository
│  │  │      ├─ service/          # 商業邏輯層
│  │  │      └─ Hello.java        # Spring Boot 啟動入口
│  │  │
│  │  └─ resources/
│  │      └─ application.yml      # 系統設定檔
│  │
│  └─ test/
│      ├─ java/
│      │  └─ training/web/
│      │      ├─ integration/     # Integration Test
│      │      └─ service/         # Unit Test
│      │
│      └─ resources/
│          └─ application-test.yml
│
├─ static/                        # 靜態測試資源
│  ├─ imgs/
│  └─ test.html
│
├─ pom.xml                        # Maven 專案設定
├─ README.md                      # 專案說明文件
└─ .gitignore

```
---

## 📖 學習重點

### 🔹 RESTful API Design
- 使用 Spring Boot 建立完整 CRUD API
- 透過 `@RestController` 與 Mapping Annotation 實作 RESTful 架構
- 統一 API Response 格式，提升前後端整合一致性

### 🔹 Layered Architecture
- 採用 Controller → Service → Repository 分層設計
- 將商業邏輯與資料存取分離，提升維護性與擴展性
- 使用 DTO 隔離 API 與 Entity

### 🔹 Database Integration
- 使用 Spring Data JPA 與 Hibernate 操作 SQL Server
- 透過 Entity Mapping 管理資料表關聯
- Hibernate 自動處理 SQL 與 Transaction Management

### 🔹 MQTT Event-Driven Messaging
- 整合 MQTT Publisher / Subscriber
- 使用 RabbitMQ MQTT Plugin 作為 Message Broker
- 實作非同步事件驅動架構（Event-Driven Architecture）

### 🔹 Scheduler & Background Jobs
- 使用 Spring Scheduler 執行定時任務
- 自動化背景資料同步與 MQTT 訊息推送

### 🔹 Exception Handling
- 使用 `@RestControllerAdvice` 統一管理例外處理
- 建立一致化錯誤回應格式
- 提升 API 可維護性與除錯效率

### 🔹 Global Request Body Validation
- 使用 `RequestBodyValidationAdvice` 集中驗證所有 `@RequestBody`
- 類似 ASP.NET ActionFilter 機制
- 減少重複驗證代碼，提升維護性
- 支援 Jakarta Bean Validation 標準註解（`@NotBlank`, `@NotNull` 等）
- 統一驗證失敗回應格式

### 🔹 Testing
- 使用 JUnit 5 撰寫 Unit Test 與 Integration Test
- 驗證 Service 邏輯與完整 API Flow
- 提升系統穩定性與程式品質

### 🔹 Maven Project Management
- 使用 Maven 管理依賴與建置流程
- 支援專案編譯、測試與封裝
- 提供一致化開發環境

---

## 📄 授權

本專案僅作為學習用途。