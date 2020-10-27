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

import com.ptc.helplife.Entity.Solicitacao;
import com.ptc.helplife.service.SolicitacaoService;

@RestController // indica classe controle;
@RequestMapping("/api/v1/helplife")
@CrossOrigin(origins = "*")
public class SolicitacaoControllerDAO {

    private SolicitacaoService service = new SolicitacaoService();

    @GetMapping("/solicitacao")
    public List<Solicitacao> get() {
        Map<String, Object> criteria = new HashMap<>();
        List<Solicitacao> solicitacaoList = service.readByCriteria(criteria);
        //System.out.println(solicitacaoList);
        return solicitacaoList;
    }

    @GetMapping("/solicitacao/{id}")
    public Solicitacao getById(@PathVariable("id") Long id) {
        Solicitacao solicitacao = service.readById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
       
        return solicitacao;
    }

    @PostMapping("/solicitacao")
    public ResponseEntity post(@RequestBody Solicitacao solicitacao) {
        Map<String, String> errors = service.validate(solicitacao);
        HttpHeaders httpHeaders = new HttpHeaders();
        if (errors.isEmpty()) {
            service.create(solicitacao);
            httpHeaders.add("Location", "/usuario/" + solicitacao.getId());
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(errors, httpHeaders, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/solicitacao/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Solicitacao solicitacao) {
        solicitacao.setId(id);
        Map<String, String> errors = service.validate(solicitacao);
        HttpHeaders httpHeaders = new HttpHeaders();
        if (errors.isEmpty()) {
            service.update(solicitacao);
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(errors, httpHeaders, HttpStatus.NOT_ACCEPTABLE);
        }
    }
      @DeleteMapping("/solicitacao/{id}") public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

  	@GetMapping("/solicitacao/ativa/{id}")
  	public ResponseEntity ativaSolicitacao(@PathVariable("id") Long id) {
  		Solicitacao solicitacao = service.readById(id);
  		HttpHeaders httpHeaders = new HttpHeaders();
  		if (solicitacao != null) {
  			solicitacao.setStatus(Solicitacao.StatusSolicitacao.STATUS_ATIVA);
  			service.update(solicitacao);
  			return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
  		} else {
  			return new ResponseEntity<>(null, httpHeaders, HttpStatus.NOT_ACCEPTABLE);
  		}
  	}

  	@GetMapping("/solicitacao/inativa/{id}")
  	public ResponseEntity desativaSolicitacao(@PathVariable("id") Long id) {
  		Solicitacao solicitacao = service.readById(id);
  		HttpHeaders httpHeaders = new HttpHeaders();
  		if (solicitacao != null) {
  			solicitacao.setStatus(Solicitacao.StatusSolicitacao.STATUS_INATIVA);
  			service.update(solicitacao);
  			return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
  		} else {
  			return new ResponseEntity<>(null, httpHeaders, HttpStatus.NOT_ACCEPTABLE);
  		}
  	}

}

