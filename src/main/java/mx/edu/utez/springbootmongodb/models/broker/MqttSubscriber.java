package mx.edu.utez.springbootmongodb.models.broker;

import com.fasterxml.jackson.databind.ObjectMapper;
import mx.edu.utez.springbootmongodb.models.record.Record;
import mx.edu.utez.springbootmongodb.models.record.RecordRepository;
import org.eclipse.paho.mqttv5.client.*;
import org.eclipse.paho.mqttv5.client.persist.MemoryPersistence;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MqttSubscriber implements MqttCallback {
    private final RecordRepository repository;

    // LOCAL MqttSubscriber

    public MqttSubscriber(RecordRepository repository) {
        this.repository = repository;
    }

    private static final String brokerUrl = "tcp://broker.emqx.io:1883";
    private static final String clientId = "mqttx_191af3a7";
    private static final String topic = "simbba/localTopic";
    private static final int subQos = 0; // es el nivel de qos del subscriber


    public void subscribe() {

        try {
            MqttClient client = new MqttClient(brokerUrl, clientId, new MemoryPersistence());
            MqttConnectionOptions options = new MqttConnectionOptions();
            options.setCleanStart(true);
            client.connect(options);
            client.setCallback(this); // se usaran los metodos de la Interfaz Callback implementados en en esta clase
            client.subscribe(topic, subQos);

            System.out.println("Mqtt subscriber listening...");

        } catch (MqttException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override // este metodo nos permite recibir el mensaje enviado por el esp
    public void messageArrived(String topic, MqttMessage mqttMessage) {
        try {

            String payload = mqttMessage.toString();
            ObjectMapper mapper = new ObjectMapper();
            Record record = mapper.readValue(payload, Record.class);
            //record.setDateAndTime(LocalDateTime.now().minusHours(6).toString()); // por alguna razon ahora no es necesario restar 6 horas
            record.setDateAndTime(LocalDateTime.now().toString());
            System.out.println("payload -> " + mqttMessage + "\n");
            System.out.println("Objeto -> " + record + "\n");

            repository.save(record);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


//<editor-fold desc="otros metodos mqtt">

    @Override
    public void deliveryComplete(IMqttToken token) {

    }

    @Override
    public void connectComplete(boolean reconnect, String serverURI) {

    }

    @Override
    public void authPacketArrived(int reasonCode, MqttProperties mqttProperties) {

    }

    @Override
    public void disconnected(MqttDisconnectResponse mqttDisconnectResponse) {

    }

    @Override
    public void mqttErrorOccurred(MqttException e) {

    }
//</editor-fold>


}
