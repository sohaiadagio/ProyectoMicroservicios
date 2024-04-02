package edu.tienda.core.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.tienda.core.domain.Cliente;
import edu.tienda.core.exceptions.BadRequestException;
import edu.tienda.core.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("/clientes")
public class ClienteRestController {

    private List<Cliente> clientes = new ArrayList<>(Arrays.asList(
            new Cliente("arm", "1234", "Armstrong"),
            new Cliente("ald", "1234", "Alldrin"),
            new Cliente("col", "1234", "Collins")

    ));
    // sobrecargade metodos

    @GetMapping // mostrar todos los clientes
    public ResponseEntity<?> /*wildcard*/getClientes() {
/*Este “wildcard” permite mayor flexibilidad ante un cambio en el cuerpo del método,
sobre todo que ya no debemos preocuparnos por definir que tipo de dato se retorna. Por ejemplo,
puede que el día de mañana nuestra Lista de clientes se transforme en “Map” o “Set”, y por lo
tanto, este cambio no afectará la firma del método. */
        return ResponseEntity.ok(clientes);
    }

    /*
     * @GetMapping("/clientes/{userName}") // buscar cliente por userName
     * public Cliente getCliente(@PathVariable String userName) {
     * for (Cliente cli : clientes) {
     * if (cli.getUsername().equalsIgnoreCase(userName)) {
     * return cli;
     * 
     * }
     * 
     * }
     * return null;
     * 
     * }
     */

    @GetMapping("/{userName}") // buscar cliente por userName orientado al uso de streams combinado con
           // funciones lambda,

          public ResponseEntity<?> getCliente(@PathVariable String userName) {
            if (userName.length() !=3) {
            throw new BadRequestException("El parametro nombre de usuario debe tener 3 caracteres");
                
            }
            return clientes.stream()./*En primer lugar se convierte la lista de clientes a tipo “stream” dándonos la posibilidad
            de ejecutar algunos métodos útiles. */
            filter(cliente -> cliente.getUsername().equalsIgnoreCase(userName))./*En este caso utilizamos “filter” que es el que necesitamos
            para realizar un filtrado por “userName”. */
            findFirst()./*En segundo término se ejecuta el método
            “findFirst()” que retornará un opcional de cliente. */
            map(ResponseEntity::ok)./*El método “map” transformara el
            “Optional<Cliente>” en un “Optional<ResponseEntity<Cliente>>” y retornará este objeto
            directamente. Esto equivale a la siguiente codificación: “return ResponseEntity.ok(cliente)”. */
            orElseThrow(() -> new ResourceNotFoundException("Cliente " + userName + " no encontrado"));/*En el caso
            de que el opcional de cliente no contenga un objeto, se ejecutará la última línea que provocará la
            excepción. Luego */
       }





    // public ResponseEntity<?> getCliente(@PathVariable String userName) {

    //     for (Cliente cliente : clientes) {
    //         if (cliente.getUsername().equals(userName)) {
    //             return ResponseEntity.ok(cliente);
                
    //         }
            
    //     }
    //     throw new ResolutionException("Cliente no encontrado");/*En este caso, Spring Boot generará ese código de respuesta por nosotros. Spring se
    //     encargará de generar el “ResponseEntity.notFound().build()” cuando detecte en tiempo de
    //     ejecución que se ha lanzado la excepcion “ResourceNotFoundException”. Este comportamiento,
    //     se logra gracias al decorador @ResponseStatus situado en la parte superior de la clase
    //     “ResourceNotFoundException”. */
    //   }

    @PostMapping
    public ResponseEntity<?> altaCliente(@RequestBody Cliente cliente) {
        clientes.add(cliente);

        //Obteniend URL de servicio
        URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{userName}")
        .buildAndExpand(cliente.getUsername())
        .toUri();
        return ResponseEntity.created(location).body(cliente);
    }

    @PutMapping

    public ResponseEntity<?>/* clase de tipo envoltorio ResponseEntity */ modificacionCliente(@RequestBody Cliente cliente) {

        Cliente clienteEncontrado = clientes.stream()
                .filter(cli -> cli.getUsername().equalsIgnoreCase(cliente.getUsername())).findFirst().orElseThrow();

        clienteEncontrado.setPassword(cliente.getPassword());
        clienteEncontrado.setNombre(cliente.getNombre());

        return ResponseEntity.ok(clienteEncontrado);
    }

    @DeleteMapping("/{userName}") // buscar cliente por userName orientado al uso de streams combinado con
                                  // funciones lambda,
    public ResponseEntity<?> deleteCliente(@PathVariable String userName) {
        Cliente clienteEncontrado = clientes.stream().

                filter(cli -> cli.getUsername().equalsIgnoreCase(userName)).findFirst().orElseThrow();
        clientes.remove(clienteEncontrado);

        return ResponseEntity.noContent().build();
    }
}
