package com.ptc.helplife.controllerDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptc.helplife.Entity.Campanha;
import com.ptc.helplife.service.CampanhaService;

@RestController // indica classe controle;
@RequestMapping("/api/v1/helplife")
@CrossOrigin(origins = "*")
public class CampanhaControllerDAO {

    private CampanhaService service = new CampanhaService();

    @GetMapping("/campanha")
    public List<Campanha> get() {
        Map<String, Object> criteria = new HashMap<>();
        List<Campanha> campanhaList = service.readByCriteria(criteria);
        //System.out.println(campanhaList);
        return campanhaList;
    }

    @GetMapping("/campanha/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Campanha campanha = service.readById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        if (campanha != null) {
            return new ResponseEntity<>(campanha, httpHeaders, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(campanha, httpHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/campanha")
    public ResponseEntity<?> post(@RequestBody Campanha campanha) {
        Map<String, String> errors = service.validate(campanha);
        HttpHeaders httpHeaders = new HttpHeaders();
        if (errors.isEmpty()) {
            service.create(campanha);
            httpHeaders.add("Location", "/campanha/" + campanha.getId());
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(errors, httpHeaders, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/campanha/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Campanha campanha) {
        campanha.setId(id);
        Map<String, String> errors = service.validate(campanha);
        HttpHeaders httpHeaders = new HttpHeaders();
        if (errors.isEmpty()) {
            service.update(campanha);
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(errors, httpHeaders, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/campanha/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

}
