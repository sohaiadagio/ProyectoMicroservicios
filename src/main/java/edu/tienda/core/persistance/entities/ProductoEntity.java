package edu.tienda.core.persistance.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) /*utilizaremos una generación mediante una secuencia que es la más
    usual en Postgres. */
    private Integer id;

    private String nombre;
    private Double precio;
    private Integer stock;
    
}
