package edu.tienda.core.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import edu.tienda.core.domain.Producto;
import edu.tienda.core.persistance.entities.ProductoEntity;
import lombok.extern.slf4j.Slf4j;

//@Primary //diferenciador
@Service("MEMORY")
@ConditionalOnProperty(value = "productos.estrategia", havingValue = "EN_MEMORIA")
@Slf4j
@Lazy // para inicializar el objeto de productosServicesIml en la ejecucion
public class ProductosServicesImpl implements ProductoService {

    private List<Producto> productos = new ArrayList<>(Arrays.asList(
            new Producto(1, "Smart Tv", 9000.0, 3),
            new Producto(2, "Pc Notebook", 15000.0, 10),
            new Producto(3, "Tablet", 8000.0, 5)

    ));
    @Override
    public List<Producto> getProductos() {

        return productos;
    }

    @Override
public void saveProducto(Producto producto) {
}
@Override
public void deleteProducto(Integer id) {
}

    public ProductosServicesImpl() {
        log.info("Se esta construyendo un objeto de la clase ProductosServicesImpl ");
    }

    @Override
    public Optional<ProductoEntity> getProductoPorID(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductoPorID'");
    }

    @Override
    public Producto updateProfile(Producto profileDetails, Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProfile'");
    }



 
}
