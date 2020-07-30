package guru.springframework.msscbrewery.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

//Añade los amanejadores de exepciones definidos aqui a cada controlador del proyecto
@ControllerAdvice
public class MvcExeptionHandler {

    //Esta anotacion sirve para cualquier execepcion de tipo ConstraintViolationException que se lanzada en cualquier metodo dentro del controlador
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> violationHandler(ConstraintViolationException e){
        List<String> exceptions = new ArrayList<>(e.getConstraintViolations().size());
        e.getConstraintViolations().forEach(violation -> exceptions.add(violation.getMessage()));
        return new ResponseEntity<>(exceptions, HttpStatus.BAD_REQUEST);
    }

    //BindException son los errores al hacer el binding al recibir una peticion al controlador y este tiene la anotacion en un parametro @RequestBody
    @ExceptionHandler(BindException.class)
    public ResponseEntity<List> bindErrorHandler(BindException e) {
        return new ResponseEntity<>(e.getAllErrors(), HttpStatus.BAD_REQUEST);
    }
}
