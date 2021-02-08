package appline;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class Messenger {

    @GetMapping("/home")
    public ModelAndView home() {
        return new ModelAndView("home");
    }

    @GetMapping("/generation")
    public String massGenerationGet(Model model) {
        ModelAndView modelAndView = new ModelAndView("generateUsers.mustache");
        modelAndView.addObject("generateUsers", new GenerateUsers());
        return "generateUsers";
    }

    @RequestMapping(value = "/generationUser", method = RequestMethod.POST)
    public ResponseEntity massGeneratorPost(@RequestBody GenerateUsers generateUsers) throws IOException {
        String nameKey = generateUsers.getNameKey();
        Integer amountUsers = generateUsers.getAmountUsers();
        Map<String, Object> json = Client.sendCommandToGenerator(String.format("http://perf-ytank1:8080/generator?type=D2C_AY&totalCount=%d&concurrency=4&redisType=list&redisOptionalKey=%s",
                amountUsers, nameKey));
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("result",json.toString());
        return ResponseEntity.ok().body(resultMap);
    }

    @GetMapping("/receiveSimCards")
    public ModelAndView receiveSimCards(Model model) {
        ModelAndView modelAndView = new ModelAndView("receiveSimCards");
        modelAndView.addObject("generateUsers", new GenerateUsers());
        return modelAndView;

    }

    @PostMapping("/receiveSimCards")
    public ResponseEntity receiveSimCardsPost(@RequestBody GenerateUsers generateUsers) throws IOException {
        Integer amountVacantSim = generateUsers.getAmountVacantSim();
        Map<String, Object> json = Client.sendCommandToGenerator(String.format("http://perf-ytank1:8080/vacantSim/phone/upload?limit=%d",
                amountVacantSim));
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("result",json.toString());
        return ResponseEntity.ok().body(resultMap);
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public ResponseEntity getStatus() throws IOException {
        LinkedHashMap<String, Object> json = (LinkedHashMap<String, Object>) Client.sendCommandToGeneratorStatus("http://perf-ytank1:8080/genstatus");
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("result",json.toString());
        return ResponseEntity.ok().body(resultMap);
    }

    @GetMapping("/stopAll")
    public void stop() {
        Client.sendCommandToGenerator("http://perf-ytank1:8080/stopAll");
    }

    @GetMapping("/keyRedis")
    public Map<String, Object> keyRedis() throws IOException {
//        response.setContentType("application/json");
        Map<String, Object> json = (Map<String, Object>) Client.sendCommandToGenerator("http://perf-ytank1:8080/redisKeys");
        return json;
    }
}
