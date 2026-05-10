package training.web.mqtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import training.web.dto.CurrencyMessageDto;
import training.web.service.CurrencyService;

@Component
public class CurrencyConsumerHandler {

    @Autowired
    private CurrencyService currencyService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @ServiceActivator(inputChannel = "mqttInputChannel") // 要接收哪個 Channel 的訊息
    public void receive(String payload) {

        try {
            // 1️⃣ JSON → DTO
            CurrencyMessageDto msg = objectMapper.readValue(payload, CurrencyMessageDto.class);

            // 2️⃣ 透過Service 存 DB
            currencyService.SaveCurrency(msg);

            System.out.println("MQTT 已存入 DB: " + msg.getCode());

        } catch (Exception e) {
            System.out.println("JSON 解析失敗: " + payload);
            e.printStackTrace();
        }
    }
}