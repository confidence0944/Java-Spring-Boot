package training.web.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.messaging.MessageChannel;

@Configuration
public class MqttConfig {

    private final MqttProperties mqttProperties;

    public MqttConfig(MqttProperties mqttProperties) {
        this.mqttProperties = mqttProperties;
    }

    /**
     * MQTT Client Factory
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {

        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();

        MqttConnectOptions options = new MqttConnectOptions();

        options.setServerURIs(new String[] { mqttProperties.getUrl() });
        options.setUserName(mqttProperties.getUsername());
        options.setPassword(mqttProperties.getPassword().toCharArray());

        options.setAutomaticReconnect(true);
        options.setCleanSession(true);

        factory.setConnectionOptions(options);

        return factory;
    }

    /**
     * MQTT 訊息通道
     */
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel(); // 建立一條 MQTT channel 為mqttInputChannel → Consumer 的傳輸管道
    }

    /**
     * MQTT Consumer
     */
    @Bean
    public MessageProducer mqttInbound() {

        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                mqttProperties.getClientId(),
                mqttClientFactory(),
                mqttProperties.getTopic());

        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());

        return adapter;
    }
}
