package com.aplana;

import com.aplana.Service.ClientImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(com.aplana.Service.ClientImpl.class);
        ClientImpl client = applicationContext.getBean(ClientImpl.class);
        try {
            while (true) {
                client.sendCommandToGenerator();
            }
        }
        catch (RuntimeException e) {
        }
    }
}
