package appline;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Client {

    public static Object sendCommandToGeneratorStatus(String command) throws RuntimeException {
        ResponseEntity<Object> result = new RestTemplate().getForEntity(command, Object.class);
        return (LinkedHashMap<String, Object>) ((ArrayList<Object>) result.getBody()).get(0);
    }

    public static Map<String, Object> sendCommandToGenerator(String command) throws RuntimeException {
        return new RestTemplate().getForObject(command, Map.class);
    }
}
