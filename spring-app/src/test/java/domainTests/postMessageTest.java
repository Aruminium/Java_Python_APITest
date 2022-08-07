package domainTests;

import com.example.springapp.domain.postMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class postMessageTest {
    @BeforeAll
    static void beforeAll() {
        System.out.println("Start PostTest");
    }

    @Test
    void PostTest() throws JsonProcessingException {
        final String message = "ネコ";
        final postMessage postMessage = new postMessage(message);
        final String res = postMessage.Post();
        System.out.println(res);
        assertEquals(message, res, "message = res の検証");
    }
}
