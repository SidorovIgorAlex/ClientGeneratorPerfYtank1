package appline;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Map;

public class Client {

    public static Object sendCommandToGeneratorStatus(String command) throws RuntimeException {
        ResponseEntity<Object> result = new RestTemplate().getForEntity(command, Object.class);
        return ((ArrayList<Object>) result.getBody()).get(0);
    }

    public static Object sendCommandToGenerator(String command) throws RuntimeException {
        Object result;
        if(command.contains("redisKeys")){
            result = new RestTemplate().getForObject(command, String.class);
        } else {
            result = new RestTemplate().getForObject(command, Map.class);
        }
        return result;
    }
}
