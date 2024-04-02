package edu.tienda.core.services;

import java.util.List;
import java.util.Optional;

import edu.tienda.core.domain.Producto;
import edu.tienda.core.persistance.entities.ProductoEntity;

public interface ProductoService {

    public List<Producto> getProductos();
    public Optional<ProductoEntity> getProductoPorID(Integer id);
    public void saveProducto(Producto producto);
    public void deleteProducto(Integer id);
    public Producto updateProfile(Producto profileDetails, Integer id);

    
    
}
