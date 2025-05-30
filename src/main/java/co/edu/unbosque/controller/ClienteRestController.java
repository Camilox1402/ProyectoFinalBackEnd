package co.edu.unbosque.controller;
import co.edu.unbosque.entity.Cliente;
import co.edu.unbosque.service.api.ClienteServiceAPI;
import co.edu.unbosque.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/cliente")
public class ClienteRestController {

    @Autowired
    private ClienteServiceAPI clienteServiceAPI;

    @GetMapping(value="/getAll")
    //ResponseEntity List<Cliente> getAll(){
    public List<Cliente> getAll(){
        return clienteServiceAPI.getAll();
    }
    


    @PostMapping(value="/saveCliente")
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
    	Cliente obj = clienteServiceAPI.save(cliente);
        return new ResponseEntity<Cliente>(obj, HttpStatus.OK); 
    }

    @GetMapping(value="/findRecord/{id}")
    public ResponseEntity<Cliente> getClienteoById(@PathVariable Long id)
            throws ResourceNotFoundException {
    	Cliente cliente = clienteServiceAPI.get(id);
        if (cliente == null){
            new ResourceNotFoundException("Record not found for <Cliente>"+id);
        }
        return ResponseEntity.ok().body(cliente);
    }

    @DeleteMapping(value="/deleteCliente/{id}")
    public ResponseEntity<Cliente> delete(@PathVariable Long id){
    	Cliente cliente = clienteServiceAPI.get(id);
        if (cliente != null){
        	clienteServiceAPI.delete(id);
        }else{
            return new ResponseEntity<Cliente>(cliente, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }
    
    @GetMapping("/existePorCorreo/{correo}")
    public ResponseEntity<Boolean> existePorCorreo(
            @PathVariable("correo") String correoCliente) {
        try {
            List<Cliente> clientes = clienteServiceAPI.getAll();
            boolean existe = clientes.stream()
                .anyMatch(c -> c.getCorreoCliente() != null && 
                             c.getCorreoCliente().equalsIgnoreCase(correoCliente));
            return ResponseEntity.ok(existe);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
}