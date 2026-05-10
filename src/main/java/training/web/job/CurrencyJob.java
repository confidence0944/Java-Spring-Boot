package training.web.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import training.web.config.MqttPublisher;

@Component
public class CurrencyJob {

    private final MqttPublisher mqttPublisher;

    public CurrencyJob(MqttPublisher mqttPublisher) {
        this.mqttPublisher = mqttPublisher;
    }

    // @Scheduled(fixedRate = 5000) // 每 5 秒執行一次（固定間隔，不等待前一次結束）
    // @Scheduled(fixedDelay = 5000) // 每 5 秒執行一次（固定間隔，等待前一次結束後再開始）
    @Scheduled(cron = "*/5 * * * * *")
    public void runJob() {
        try {
            String message = "Currency Job running... " + System.currentTimeMillis();

            mqttPublisher.publish("test/topic", message);

            System.out.println("PUBLISHED: " + message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
