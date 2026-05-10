package training.web.config;

import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Component;

@Component
public class MqttPublisher {

    private final MqttProperties mqttProperties;
    private MqttClient client;

    public MqttPublisher(MqttProperties mqttProperties) {
        this.mqttProperties = mqttProperties;
        init();
    }

    private void init() {
        try {
            client = new MqttClient(
                    mqttProperties.getUrl(),
                    mqttProperties.getClientId());

            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);

            options.setUserName(mqttProperties.getUsername());
            options.setPassword(mqttProperties.getPassword().toCharArray());

            client.connect(options);

            System.out.println("MQTT connected: " + mqttProperties.getUrl());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publish(String topic, String message) {
        try {

            if (!mqttProperties.isEnabled()) {
                System.out.println("MQTT DISABLED " + message);
                return;
            }

            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(0);

            client.publish(topic, mqttMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}