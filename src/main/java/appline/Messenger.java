package appline;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
public class Messenger {

    @GetMapping("/home")
    public ModelAndView home() {
        return new ModelAndView("home");
    }

    @GetMapping("/generation")
    public ModelAndView massGenerationGet(Model model) {
        ModelAndView modelAndView = new ModelAndView("generateUsers");
        modelAndView.addObject("generateUsers", new GenerateUsers());
        return modelAndView;
    }

    @PostMapping("/generation")
    public Map<String, Object> massGeneratorPost(GenerateUsers generateUsers, HttpServletResponse response) throws IOException {
        String nameKey = generateUsers.getNameKey();
        Integer amountUsers = generateUsers.getAmountUsers();
        response.setContentType("application/json");
        Map<String, Object> json = Client.sendCommandToGenerator(String.format("http://perf-ytank1:8080/generator?type=D2C_AY&totalCount=%d&concurrency=4&redisType=list&redisOptionalKey=%s",
                amountUsers, nameKey));
        return json;
    }

    @GetMapping("/receiveSimCards")
    public ModelAndView receiveSimCards(Model model) {
        ModelAndView modelAndView = new ModelAndView("receiveSimCards");
        modelAndView.addObject("generateUsers", new GenerateUsers());
        return modelAndView;

    }

    @PostMapping("/receiveSimCards")
    public Map<String, Object> receiveSimCardsPost(GenerateUsers generateUsers, HttpServletResponse response) throws IOException {
        Integer amountVacantSim = generateUsers.getAmountVacantSim();
        System.out.println(amountVacantSim);
        response.setContentType("application/json");
        Map<String, Object> json = Client.sendCommandToGenerator(String.format("http://perf-ytank1:8080/vacantSim/phone/upload?limit=%d",
                amountVacantSim));
        return json;
    }

    @GetMapping("/status")
    public Map<String, Object> getStatus(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        Map<String, Object> json = Client.sendCommandToGenerator("http://perf-ytank1:8080/genstatus");
        return json;
    }

    @GetMapping("/stopAll")
    public void stop() {
        Client.sendCommandToGenerator("http://perf-ytank1:8080/stopAll");
    }

    @GetMapping("/keyRedis")
    public Map<String, Object> keyRedis(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        Map<String, Object> json = Client.sendCommandToGenerator("http://perf-ytank1:8080/redisKeys");
        return json;
    }
}
