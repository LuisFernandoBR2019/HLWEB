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

import com.ptc.helplife.Entity.Usuario;
import com.ptc.helplife.Entity.UsuarioComum;
import com.ptc.helplife.Entity.UsuarioHemocentro;
import com.ptc.helplife.service.UsuarioService;

@RestController // indica classe controle;
@RequestMapping("/api/v1/helplife")
@CrossOrigin(origins = "*")
public class UsuarioControllerDAO {

    private UsuarioService service = new UsuarioService();

    @GetMapping("/usuario")
    public List<Usuario> get() {
        Map<String, Object> criteria = new HashMap<>();
        List<Usuario> usuarioList = service.readByCriteria(criteria);
        System.out.println(usuarioList);
        return usuarioList;
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Usuario usuario = service.readById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        if (usuario != null) {
            return new ResponseEntity<>(usuario, httpHeaders, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(usuario, httpHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/hemocentro")
    public ResponseEntity post(@RequestBody UsuarioHemocentro usuario) {
        Map<String, String> errors = service.validate(usuario);
        HttpHeaders httpHeaders = new HttpHeaders();
        if (errors.isEmpty()) {
            service.create(usuario);
            httpHeaders.add("Location", "/usuario/" + usuario.getId());
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(errors, httpHeaders, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/usuariocomum")
    public ResponseEntity post(@RequestBody UsuarioComum usuario) {
        Map<String, String> errors = service.validate(usuario);
        HttpHeaders httpHeaders = new HttpHeaders();
        if (errors.isEmpty()) {
            service.create(usuario);
            httpHeaders.add("Location", "/usuario/" + usuario.getId());
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(errors, httpHeaders, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/usuariocomum/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody UsuarioComum usuario) {
        usuario.setId(id);
        Map<String, String> errors = service.validate(usuario);
        HttpHeaders httpHeaders = new HttpHeaders();
        if (errors.isEmpty()) {
            service.update(usuario);
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(errors, httpHeaders, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/hemocentro/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody UsuarioHemocentro usuario) {
        usuario.setId(id);
        Map<String, String> errors = service.validate(usuario);
        HttpHeaders httpHeaders = new HttpHeaders();
        if (errors.isEmpty()) {
            service.update(usuario);
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(errors, httpHeaders, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/usuario/{id}")
    public void delete(@PathVariable("id") Long id
    ) {
        service.delete(id);
    }

}

