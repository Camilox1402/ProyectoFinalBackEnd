package co.edu.unbosque.controller;
import co.edu.unbosque.entity.Producto;

import co.edu.unbosque.service.api.ProductoServiceAPI;
import co.edu.unbosque.utils.ResourceNotFoundException;
import co.edu.unbosque.utils.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoRestController {

    @Autowired
    private ProductoServiceAPI productoServiceAPI;
    
    @Autowired
    private Util util;

    @GetMapping(value="/getAll")
    //ResponseEntity List<Producto> getAll(){
    public List<Producto> getAll(){
        return productoServiceAPI.getAll();
    }

    @PostMapping("/saveProducto")
    public ResponseEntity<Producto> saveProducto(@RequestBody Producto producto) {
        try {
            Producto savedProducto = util.saveWithDetails(producto);
            return new ResponseEntity<>(savedProducto, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error al guardar el producto: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value="/findRecord/{id}")
    public ResponseEntity<Producto> getProductooById(@PathVariable Long id)
            throws ResourceNotFoundException {
    	Producto producto = productoServiceAPI.get(id);
        if (producto == null){
            new ResourceNotFoundException("Record not found for <Producto>"+id);
        }
        return ResponseEntity.ok().body(producto);
    }

    @DeleteMapping(value="/deleteProducto/{id}")
    public ResponseEntity<Producto> delete(@PathVariable Long id){
    	Producto producto = productoServiceAPI.get(id);
        if (producto != null){
        	productoServiceAPI.delete(id);
        }else{
            return new ResponseEntity<Producto>(producto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Producto>(producto, HttpStatus.OK);
    }
}