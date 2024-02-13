package mx.edu.utez.springbootmongodb;

import jakarta.annotation.PostConstruct;
import mx.edu.utez.springbootmongodb.models.broker.MqttSubscriber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMongoDbApplication {
    private final MqttSubscriber subscriber;
    public SpringBootMongoDbApplication(MqttSubscriber subscriber) {
        this.subscriber = subscriber;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongoDbApplication.class, args);

    }
    // este metodo hace que el subscriber (que seria un bean) se inicialice y justo despues de inicializarse se ejecute su metodo subscribe
    // el metodo subscribe de momento es nuestro listener mqtt
    @PostConstruct
    public void subscribeToMqttTopic(){
        subscriber.subscribe();
    }

}
