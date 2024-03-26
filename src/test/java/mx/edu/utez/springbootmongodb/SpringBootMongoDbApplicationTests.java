package mx.edu.utez.springbootmongodb;

import mx.edu.utez.springbootmongodb.services.TrashcanService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SpringBootMongoDbApplicationTests {

    @Test
    void testGetLevel() {
        TrashcanService trashcanService = new TrashcanService(null, null);
        Double distance = 12.0; // Distancia detectada
        Double expectedLevel = 13.3; // Porcetnaje esperado
        assertEquals(expectedLevel, trashcanService.getLevel(distance));
    }

}
