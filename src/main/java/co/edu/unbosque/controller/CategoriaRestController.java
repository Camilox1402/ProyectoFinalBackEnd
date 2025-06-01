package co.edu.unbosque.controller;
import co.edu.unbosque.entity.Categoria;
import co.edu.unbosque.service.api.CategoriaServiceAPI;
import co.edu.unbosque.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:8181",maxAge = 3600)
@RestController
@RequestMapping("/categoria")
public class CategoriaRestController {

    @Autowired
    private CategoriaServiceAPI categoriaServiceAPI;

    @GetMapping(value="/getAll")
    //ResponseEntity List<Categoria> getAll(){
    public List<Categoria> getAll(){
        return categoriaServiceAPI.getAll();
    }

    @GetMapping("/listarCategorias")
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return new ResponseEntity<>(categoriaServiceAPI.getAll(), HttpStatus.OK);
    }

    @PostMapping(value="/saveCategoria")
    public ResponseEntity<Categoria> save(@RequestBody Categoria categoria){
    	Categoria obj = categoriaServiceAPI.save(categoria);
        return new ResponseEntity<Categoria>(obj, HttpStatus.OK); // 200
    }

    @GetMapping(value="/findRecord/{id}")
    public ResponseEntity<Categoria> getCategoriaoById(@PathVariable Long id)
            throws ResourceNotFoundException {
    	Categoria categoria = categoriaServiceAPI.get(id);
        if (categoria == null){
            new ResourceNotFoundException("Record not found for <Categoria>"+id);
        }
        return ResponseEntity.ok().body(categoria);
    }

    @DeleteMapping(value="/deleteCategoria/{id}")
    public ResponseEntity<Categoria> delete(@PathVariable Long id){
    	Categoria categoria = categoriaServiceAPI.get(id);
        if (categoria != null){
        	categoriaServiceAPI.delete(id);
        }else{
            return new ResponseEntity<Categoria>(categoria, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
    }
}