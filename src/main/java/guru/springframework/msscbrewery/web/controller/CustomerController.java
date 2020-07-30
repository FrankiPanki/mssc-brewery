package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    public final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    ResponseEntity<CustomerDto> getById(@PathVariable(value = "id") UUID id){
        return new ResponseEntity(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    ResponseEntity create(@RequestBody CustomerDto customerDto, @PathVariable(value = "id") UUID id ){
        CustomerDto save = customerService.saveNewCustomer(customerDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/customer/"+ save.getId().toString());
        return new ResponseEntity(headers,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void actualizar(@RequestBody CustomerDto customerDto, @PathVariable(value = "id") UUID id ){
        customerService.updateCustomer(id,customerDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void borrar( @PathVariable(value = "id") UUID id){
        customerService.deleteById(id);

    }
}
