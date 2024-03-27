package mx.edu.utez.springbootmongodb.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TrashcanServiceTest {

    @Test
    void testGetLevel() {
        TrashcanService trashcanService = new TrashcanService(null, null);
        double distance = 12.0; // Distancia detectada
        double expectedLevel = 97.5; // Porcetnaje esperado
        assertEquals(expectedLevel, trashcanService.getLevel(distance));
    }
}