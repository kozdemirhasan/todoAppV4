package de.kozdemir.todoappv4;

import de.kozdemir.todoappv4.model.User;
import de.kozdemir.todoappv4.repository.TodoRepository;
import de.kozdemir.todoappv4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
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

    @Value("${db.reset}")
    private boolean dbReset;
    @Autowired
    private TodoRepository todoRepository;

    public static void main(String[] args) {
        SpringApplication.run(TodoAppV4Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        if(dbReset) {
            todoRepository.deleteAll();
            userRepository.deleteAll();

            User admin = new User("hasanmurat@abc.com", "geheim123");
            userRepository.save(admin);

            admin = new User("hmk@abc.com", "geheim123");
            userRepository.save(admin);

            admin = new User("h.murat@abc.com", "geheim123");
            userRepository.save(admin);

            admin = new User("p.parker@shield.com", "geheim123");
            userRepository.save(admin);
        }
    }


    @Bean
    public LocaleResolver localeResolver() {
        Locale.setDefault(new Locale(uiLanguage));
       /*
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setCookieName("lang");
        resolver.setCookieMaxAge(60*60*24*30);
        */
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(new Locale(uiLanguage));
        return resolver;
    }

    /* Erlaubt das Ändern der Sprache über die URL, http://www.domain.de?lang=de */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }


}
