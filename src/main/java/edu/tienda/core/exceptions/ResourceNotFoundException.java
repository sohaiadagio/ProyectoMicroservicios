package edu.tienda.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody /*@ResponseBody indica que esta excepción, al lanzarse desde algún
controlador, formará parte del cuerpo de */
@ResponseStatus(HttpStatus.NOT_FOUND)/*El anotador indicará que al lanzarse esta excepcion, se reportara con un código
de respuesta 404. Esto significa que Spring creará una “ResponseEntity” por nosotros al detectar
el lanzamiento de esta excepción. */

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message){

        super(message);
    }
    
    @Override

    public String getMessage(){

        return super.getMessage();
    }
}
