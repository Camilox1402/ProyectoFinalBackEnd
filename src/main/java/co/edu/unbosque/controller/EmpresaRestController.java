package co.edu.unbosque.controller;
import co.edu.unbosque.entity.Empresa;
import co.edu.unbosque.service.api.EmpresaServiceAPI;
import co.edu.unbosque.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:8181",maxAge = 3600)
@RestController
@RequestMapping("/empresa")
public class EmpresaRestController {

    @Autowired
    private EmpresaServiceAPI empresaServiceAPI;

    @GetMapping(value="/getAll")
    //ResponseEntity List<Auditoria> getAll(){
    public List<Empresa> getAll(){
        return empresaServiceAPI.getAll();
    }


    @PostMapping(value="/saveEmpresa")
    public ResponseEntity<Empresa> save(@RequestBody Empresa empresa){
    	Empresa obj = empresaServiceAPI.save(empresa);
        return new ResponseEntity<Empresa>(obj, HttpStatus.OK); // 200
    }

    @GetMapping(value="/findRecord/{id}")
    public ResponseEntity<Empresa> getEmpresaoById(@PathVariable Long id)
            throws ResourceNotFoundException {
    	Empresa empresa = empresaServiceAPI.get(id);
        if (empresa == null){
            new ResourceNotFoundException("Record not found for <Empresa>"+id);
        }
        return ResponseEntity.ok().body(empresa);
    }

    @DeleteMapping(value="/deleteEmpresa/{id}")
    public ResponseEntity<Empresa> delete(@PathVariable Long id){
    	Empresa empresa = empresaServiceAPI.get(id);
        if (empresa != null){
        	empresaServiceAPI.delete(id);
        }else{
            return new ResponseEntity<Empresa>(empresa, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Empresa>(empresa, HttpStatus.OK);
    }
}