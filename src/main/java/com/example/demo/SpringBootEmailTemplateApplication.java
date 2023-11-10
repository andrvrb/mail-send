package com.example.demo;

import com.example.demo.model.Mail;
import com.example.demo.service.EmailSenderService;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SpringBootEmailTemplateApplication implements ApplicationRunner{
	
	@Autowired
    private EmailSenderService emailService;
	
	private static Logger log = LoggerFactory.getLogger(SpringBootEmailTemplateApplication.class); 
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootEmailTemplateApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
        Mail mail = new Mail();
        mail.setFrom("andr-pnz@mail.ru");//replace with your desired email
        mail.setMailTo("andr-pnz@mail.ru");//replace with your desired email
        
        sendFakeNewsLetter(mail);
        sendInlinedCssEmail(mail);
	}
	
	private void sendFakeNewsLetter(Mail mail) throws MessagingException, IOException {
		
		log.info("START... Sending Fake News Letter");
		
        mail.setSubject("Email with Spring boot and thymeleaf template!");

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", "Developer!");
        model.put("location", "United States");
        model.put("sign", "Java Developer");
        model.put("type", "NEWSLETTER");
        mail.setProps(model);

        emailService.sendEmail(mail);
        log.info("END... Sending Fake News Letter");
	}
	
    private void sendInlinedCssEmail(Mail mail) throws MessagingException, IOException {
		
    	log.info("START...Sending Inlined CSS Email");

        mail.setSubject("Email with Inlined CSS Responsive Thymeleaf Template!");

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", "Peter Milanovich!");
        model.put("address", "Company Inc, 3 Abbey Road, San Francisco CA 94102");
        model.put("sign", "JavaByDeveloper");
        model.put("type", "TRANSACTIONAL");
        mail.setProps(model);

        emailService.sendEmail(mail);
        log.info("END... Sending Inlined CSS Email");
	}
}
