package mx.edu.utez.springbootmongodb;

import mx.edu.utez.springbootmongodb.models.location.Location;
import mx.edu.utez.springbootmongodb.models.record.Record;
import mx.edu.utez.springbootmongodb.models.record.RecordRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;


@SpringBootApplication
public class SpringBootMongoDbApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongoDbApplication.class, args);
    }

//    @Bean
//    CommandLineRunner runner (RecordRepository repository){
//        return args -> {
//            Location location = new Location(18.850410301711264, -99.20075160125488);
//            Record record = new Record(
//                    0.0,
//                    3,
//                    false,
//                    LocalDateTime.now().minusHours(6),
//                    // quitamos 6 horas de diferencia ya que LocalDateTime toma como referencia el UTC
//                    // en mexico (cdmx) tenemos una direrencia de -6 horas con respecto al UTC
//                    // con esto logramos capturar la hora excacta en la base de datos
//                    location // estas coordenadas son de la UTEZ
//            );
//
//            repository.insert(record);
//        };
//    }
}
