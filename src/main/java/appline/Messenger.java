package appline;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class Messenger {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("home");
    }

    @RequestMapping(value = "/generation", method = RequestMethod.GET)
    public String massGenerationGet() {
        return "generateUsers";
    }

    @RequestMapping(value = "/generationUser", method = RequestMethod.POST)
    public ResponseEntity massGeneratorPost(@RequestBody GenerateUsers generateUsers) {
        String nameKey = generateUsers.getNameKey();
        Integer amountUsers = generateUsers.getAmountUsers();
        Map<String, Object> json = (Map<String, Object>) Client.sendCommandToGenerator(String.format("http://perf-ytank1:8080/generator?type=D2C_AY&totalCount=%d&concurrency=4&redisType=list&redisOptionalKey=%s",
                amountUsers, nameKey));
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("result",json.toString());
        return ResponseEntity.ok().body(resultMap);
    }

    @RequestMapping(value = "/simCards", method = RequestMethod.GET)
    public String receiveSimCards() {
        return "receiveSimCards";
    }

    @RequestMapping(value = "/receiveSimCards", method = RequestMethod.POST)
    public ResponseEntity receiveSimCardsPost(@RequestBody GenerateUsers generateUsers) {
        Integer amountVacantSim = generateUsers.getAmountVacantSim();
        Map<String, Object> json = (Map<String, Object>) Client.sendCommandToGenerator(String.format("http://perf-ytank1:8080/vacantSim/phone/upload?limit=%d",
                amountVacantSim));
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("result",json.toString());
        return ResponseEntity.ok().body(resultMap);
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public ResponseEntity getStatus() {
        LinkedHashMap<String, Object> json = (LinkedHashMap<String, Object>) Client.sendCommandToGeneratorStatus("http://perf-ytank1:8080/genstatus");
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("result",json.toString());
        return ResponseEntity.ok().body(resultMap);
    }

    @RequestMapping(value = "/stopAll", method = RequestMethod.GET)
    public void stop() {
        Client.sendCommandToGenerator("http://perf-ytank1:8080/stopAll");
    }

    @RequestMapping(value = "/keyRedis", method = RequestMethod.GET)
    public ResponseEntity keyRedis() throws IOException {
        return ResponseEntity.ok().body(Client.sendCommandToGenerator("http://perf-ytank1:8080/redisKeys").toString());
    }
}
