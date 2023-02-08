package de.kozdemir.todoappv4;

import de.kozdemir.todoappv4.model.User;
import de.kozdemir.todoappv4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
public class TodoAppV4Application implements WebMvcConfigurer, CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Value("${ui.language}")
    private String uiLanguage;

    @Value("${db.reset}") // greift auf den Wert aus application.yaml zu
    private boolean dbReset;

    public static void main(String[] args) {
        SpringApplication.run(TodoAppV4Application.class, args);
    }

    @Bean
    public LocaleResolver localeResolver() {
        Locale.setDefault(new Locale(uiLanguage));
        //CookieLocaleResolver resolver = new CookieLocaleResolver(); // Sprache wird im Cookie des Browser gespeichert
        SessionLocaleResolver resolver = new SessionLocaleResolver(); // Sprache wird in der Session des Servers gespeichert
        resolver.setDefaultLocale(new Locale(uiLanguage));
        return resolver;
    }

    @Override
    public void run(String... args) throws Exception {


        if(dbReset) {
            userRepository.deleteAll();
            User admin = new User("hasanmurat@abc.com", "geheim123");
            userRepository.save(admin);

            admin = new User("hmk@abc.com", "geheim123");
            userRepository.save(admin);

        }
    }
}
