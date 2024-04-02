package edu.tienda.core.persistance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.tienda.core.persistance.entities.ProductoEntity;

@Repository
public interface ProductosRepository extends JpaRepository<ProductoEntity,Integer> {
    

}
