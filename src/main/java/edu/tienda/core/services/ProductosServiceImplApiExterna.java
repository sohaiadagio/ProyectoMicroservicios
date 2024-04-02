package edu.tienda.core.services;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.tienda.core.domain.Producto;
import edu.tienda.core.persistance.entities.ProductoEntity;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Lazy
@Service("API")
@ConditionalOnProperty(value = "productos.estrategia", havingValue = "EN_OTRA_API")

public class ProductosServiceImplApiExterna implements ProductoService {

 @Override
    public List<Producto> getProductos() {

        RestTemplate restTemplate = new RestTemplate();

        @SuppressWarnings("null")
        ResponseEntity<List<Producto>> response = restTemplate.exchange(
                "http://localhost:8080/tienda/api/v1/productos/fake-productos",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Producto>>() {

                });
        List<Producto> productos = response.getBody();

        return productos;

    }
 
@Override
public void saveProducto(Producto producto) {
}
@Override
public void deleteProducto(Integer id) {
}

public ProductosServiceImplApiExterna() {
        log.info("Se esta construyendo un objeto de la clase ProductosServiceImplApiExterna ");
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
