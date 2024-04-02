package edu.tienda.core.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.tienda.core.configurations.ConfigurationParameters;
import edu.tienda.core.domain.Producto;
import edu.tienda.core.persistance.entities.ProductoEntity;
import edu.tienda.core.services.ProductoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/productos")

public class ProductosControllerRest {

    // se instancia la clase servicio al estilo "Java puro"

    // @Qualifier("MEMORY")
    @Lazy /*
           * La anotación @Lazy en Spring Boot se utiliza para indicar que un bean debe
           * inicializarse de manera perezosa o "lazy".
           * Esto significa que el bean se creará y se inicializará solo cuando se
           * solicite por primera vez,
           * en lugar de hacerlo de manera ansiosa durante el inicio de la aplicación.
           */
    @Autowired
    ProductoService productosService;
    /*
     * Cuando asignas el constructor de una clase especializada a una variable de
     * tipo más general, estás utilizando el principio del polimorfismo.
     * Esto significa que puedes tratar a un objeto de la clase "ProductoService"
     * como si fuera un objeto de la clase "ProductosServiceImpl".
     */

    @Autowired
    ConfigurationParameters configurationParameters;

    @GetMapping

    public ResponseEntity<?> getProductos() {

        log.info("params " + configurationParameters.toString());
        // Se recuperan todos los productos del servicio
        List<Producto> productos = productosService.getProductos();

        // retormanos todos los productos del servicio en el body de la respuesta
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/fake-productos")

    public ResponseEntity<?> fakeProductosAPI() {

        List<Producto> productos = new ArrayList<>(Arrays.asList(
                new Producto(1, "Camiseta Juventus", 9000.0, 3),
                new Producto(2, "Camiseta River Plate", 15000.0, 10),
                new Producto(3, "Camiseta Boca Juniors", 8000.0, 5)

        ));

        // retormanos todos los productos del servicio en el body de la respuesta
        return ResponseEntity.ok(productos);
    }

    //Endpoints DB

    @PostMapping
    public ResponseEntity<?> altaProducto(@RequestBody Producto producto){

        productosService.saveProducto(producto);

        //Obteniendo URL de servicio.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(producto.getId())
                .toUri();

        return ResponseEntity.created(location).body(producto);
    }

    @DeleteMapping("/{id}") // buscar cliente por userName orientado al uso de streams combinado con
                                  // funciones lambda,
    public ResponseEntity<?> eliminarProducto(@PathVariable Integer id) {
          productosService.deleteProducto(id);
 
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}") // buscar cliente por userName orientado al uso de streams combinado con
    // funciones lambda,
public ResponseEntity<?> getProductoPorID(@PathVariable Integer id) {
    Optional<ProductoEntity> productos = productosService.getProductoPorID(id);

    // retormanos todos los productos del servicio en el body de la respuesta
    return ResponseEntity.ok(productos);
}
public ResponseEntity<?> editarProducto(@PathVariable Integer id, Producto producto) {
    Optional<ProductoEntity> productos = productosService.getProductoPorID(id);

    productosService.updateProfile(producto, id);
    productosService.saveProducto(producto);

    // retormanos todos los productos del servicio en el body de la respuesta
    return ResponseEntity.ok(productos);


}
}
