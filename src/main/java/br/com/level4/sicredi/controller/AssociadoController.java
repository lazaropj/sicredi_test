package br.com.level4.sicredi.controller;

import br.com.level4.sicredi.model.Associado;
import br.com.level4.sicredi.service.AssociadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("associado")
public class AssociadoController extends AbstractController {

    private static final Logger log = LoggerFactory.getLogger(AssociadoController.class);

    private AssociadoService associadoService;

    @Autowired
    public AssociadoController(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }

    @PostMapping("salvar")
    public ResponseEntity<Object> salvar(@RequestBody(required = true) Associado associado){
        if (associadoService.obterPorCpf(associado.getCpf()).isPresent()){
            return new ResponseEntity<>("CPF JÁ CADASTRADO", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(associadoService.salvar(associado), HttpStatus.OK);
    }
}
