package edu.tienda.core.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import edu.tienda.core.domain.Producto;
import edu.tienda.core.persistance.entities.ProductoEntity;
import edu.tienda.core.persistance.repositories.ProductosRepository;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service("BD")
@ConditionalOnProperty(value = "productos.estrategia", havingValue = "EN_BD")
public class ProductosServiceBDImpl implements ProductoService {

    @Autowired
    ProductosRepository productosRepository;

    /*Tal vez se pregunten, para qué disponemos de dos clases productos que son similares, con
mismos atributos, etc. En realidad son iguales pero con propósitos distintos. La clase “Producto”
pertenece al modelo de datos y tiene el rol de “Data Transfer Object”. Esto significa que se
utiliza para representar la información del producto en la API Rest. Mientras que la clase
“ProductoEntity” tiene un rol de tipo “Data Access Object” que tiene como propósito
transportar la información desde base de datos hasta la capa de servicio. */

    @Override // Java tradicional Version
    public List<Producto> getProductos() {

        List<ProductoEntity> productosEntities = productosRepository.findAll();
        List<Producto> productos = new ArrayList<>();

        for (ProductoEntity productoEntity : productosEntities) {
            Producto producto = new Producto();
            producto.setId(productoEntity.getId());
            producto.setNombre(productoEntity.getNombre());
            producto.setPrecio(productoEntity.getPrecio());
            producto.setStock(productoEntity.getStock());
            productos.add(producto);

        }
        return productos;

    }

   /* @Override // lambda Version
    public List<Producto> getProductos() {

        List<Producto> productos = productosRepository.findAll().stream().map(productoEntity -> {
            Producto producto = new Producto();
            producto.setId(productoEntity.getId());
            producto.setNombre(productoEntity.getNombre());
            producto.setPrecio(productoEntity.getPrecio());
            producto.setStock(productoEntity.getStock());
            productos.add(producto);
            return producto;

        }).collect(Collectors.toList());

        return productos;

    }*/

    @Override
public void saveProducto(Producto producto) {

    //Mapeo de Producto a ProductoEntity
    ProductoEntity productoEntity = new ProductoEntity();
    productoEntity.setNombre(producto.getNombre());
    productoEntity.setPrecio(producto.getPrecio());
    productoEntity.setStock(producto.getStock());

    //Persistencia
    productosRepository.save(productoEntity);
}

@SuppressWarnings("null")
public void deleteProducto(Integer id) {
    Optional<ProductoEntity> productoEntity = getProductoPorID(id);
    if (productoEntity.isPresent()) {
        productosRepository.deleteById(id);
    } else {

        log.info("No se encontro el producto con ese ID");
        // Manejo de caso en el que la entidad no existe
        // Puedes lanzar una excepción o hacer otro manejo según tu lógica de negocio
    }
}

@SuppressWarnings("null")
@Override
public Optional<ProductoEntity> getProductoPorID(Integer id) {
    return productosRepository.findById(id);
}

// update Profile
public Producto updateProfile(Producto profileDetails, Integer id) {
    Optional<ProductoEntity> productoEntity = getProductoPorID(id);

    

    profileDetails.setNombre(productoEntity.get().getNombre());
    profileDetails.setPrecio(productoEntity.get().getPrecio());
    profileDetails.setStock(productoEntity.get().getStock());
   
 

    return profileDetails;
}
}





