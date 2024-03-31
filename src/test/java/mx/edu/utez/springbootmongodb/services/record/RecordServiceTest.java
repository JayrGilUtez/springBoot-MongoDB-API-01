package mx.edu.utez.springbootmongodb.services.record;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
//import static org.junit.jupiter.api.Assertions.*;

class RecordServiceTest {
    @Test
    void lastSevenDays() {
        for (int i = 0; i < 7; i++) {
            String date = LocalDateTime.now().minusDays(i).toString().substring(0,10);
            System.out.println(date);
        }
    }
}