package appline;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Client {

//    private static final String URL_GENERATE_USERS = "http://perf-ytank1:8080/generator?type=D2C_AY&totalCount=%s&concurrency=4&redisType=list&redisOptionalKey=%s";
//    private static final String URL_STOP_ALL = "http://perf-ytank1:8080/stopAll";
//    private static final String URL_STATUS = "http://perf-ytank1:8080/genstatus";
//    private static final String URL_VACANT_SIM = "http://perf-ytank1:8080/vacantSim/phone/upload?limit=%s";
//    private static final String URL_GENERATE_ONE_USER = "http://perf-ytank1:8080/d2cAY";
//    private static final String URL_REDIS_KEY = "http://perf-ytank1:8080/redisKeys";

    public static LinkedHashMap<String, Object> sendCommandToGenerator(String command) throws RuntimeException {

//        switch (index) {
//            case "1": {
////                command = String.format(URL_GENERATE_USERS, Messenger.messageAmountUsers(), Messenger.messageNameKeyRedis());
//                break;
//            }
//            case "2": {
//                command = String.format(URL_GENERATE_ONE_USER);
//                break;
//            }
//            case "3": {
////                command = String.format(URL_VACANT_SIM, Messenger.messageAmountSimCards());
//                break;
//            }
//            case "4": {
//                command = URL_STATUS;
//                break;
//            }
//            case "5": {
//                command = URL_STOP_ALL;
//                break;
//            }
//            case "6" :{
//                command = URL_REDIS_KEY;
//                break;
//            }
//            default: throw new RuntimeException();
//        }

        ResponseEntity<Object> result = new RestTemplate().getForEntity(command, Object.class);

        return (LinkedHashMap<String, Object>) ((ArrayList<Object>) result.getBody()).get(0);
    }
}
