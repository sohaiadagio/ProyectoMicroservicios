package edu.tienda.core.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Configuration /*Los beans anotados con @Configuration, como lo denota la palabra, son clases generadas
con el propósito de servir como configuración global de la aplicación. Es decir, en estas clases
deberá radicar código fuente que tenga como finalidad ejecutar alguna configuración inicial del
sistema como es en este caso. */

@ConfigurationProperties(prefix = "app")

public class ConfigurationParameters {

    private String nombre;
    private String pais;
    private String author;
    private String lenguaje;

    

    
}
