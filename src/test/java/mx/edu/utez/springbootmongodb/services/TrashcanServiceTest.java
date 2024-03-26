package mx.edu.utez.springbootmongodb.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TrashcanServiceTest {

    @Test
    void testGetLevel() {
        TrashcanService trashcanService = new TrashcanService(null, null);
        Double distance = 12.0; // Distancia detectada
        Double expectedLevel = 13.3; // Porcetnaje esperado
        assertEquals(expectedLevel, trashcanService.getLevel(distance));
    }
}