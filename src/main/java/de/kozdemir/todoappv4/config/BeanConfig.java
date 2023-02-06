package de.kozdemir.todoappv4.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration // Wird beim Stat der Anwendung eingelesen. Beschreibt, was beim Start bereitgestellt werden soll
public class BeanConfig { // Name ist frei wählbar
/*
    // Beans können ganz neue Funktionalitäten bereitstellen oder vorhandene überschreiben

    @Bean // per default wird die Bean 1x für alle instanziert und allen zur zur Verfügung gestellt (@ApplicationScope)
    // @RequestScope // Für jede Anfrage gibt es ein neues Objekt
    // @ApplicationScope // Für alle zusammen und alle Besuche gibt es das gleiche Objekt
    @SessionScope // Jeder Besucher hat seine eine Bean
    public ShoppingCart shoppingCart() { // Methode heißt wie der Typ nur mit Kleinbuchstaben beginnend
        return new ShoppingCart();
    }

    @Bean
    @SessionScope
    public LoginService loginService() {
        return new LoginService();
    }
    */
}
