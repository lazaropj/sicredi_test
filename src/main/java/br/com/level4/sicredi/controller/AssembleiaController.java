package br.com.level4.sicredi.controller;

import br.com.level4.sicredi.model.Assembleia;
import br.com.level4.sicredi.model.Associado;
import br.com.level4.sicredi.model.Pauta;
import br.com.level4.sicredi.model.Voto;
import br.com.level4.sicredi.service.AssembleiaService;
import br.com.level4.sicredi.service.AssociadoService;
import br.com.level4.sicredi.service.PautaService;
import br.com.level4.sicredi.service.VotoService;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController("assembleia")
public class AssembleiaController extends AbstractController {

    private static final Logger log = LoggerFactory.getLogger(AssembleiaController.class);

    private AssembleiaService assembleiaService;

    private AssociadoService associadoService;

    private PautaService pautaService;

    private VotoService votoService;

    @Autowired
    public AssembleiaController(AssembleiaService assembleiaService,
                                AssociadoService associadoService,
                                PautaService pautaService,
                                VotoService votoService) {
        this.assembleiaService = assembleiaService;
        this.associadoService = associadoService;
        this.pautaService = pautaService;
        this.votoService = votoService;
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarAssembleia(@RequestBody(required = true) Assembleia assembleia){
        try{
            Pauta pautaSalva = pautaService.salvar(assembleia.getPauta());
            assembleia.setPauta(pautaSalva);
            return new ResponseEntity<>(assembleiaService.salvar(assembleia), HttpStatus.OK);
        }catch (Exception e) {
            log.error("[AssembleiaController] [cadastrarAssembleia] [{}]",  assembleia.toString(), e);
            return handleException(e);
        }
    }

    @PostMapping(path = "votar/{idAssembleia}/cpf/{cpf}/opcao/{opcao}")
    public ResponseEntity<Object> votar(@RequestParam(required = true)  Long idAssembleia,
                                        @RequestParam(required = true)  String cpf,
                                        @RequestParam(required = true)  boolean opcao){
        try{
            Optional<Assembleia> assembleia = assembleiaService.obterPorId(idAssembleia);
            if (assembleia.isPresent() ){
                Assembleia assembleiaSalva = assembleia.get();
                Pauta pautaSalva = assembleiaSalva.getPauta();
                if (pautaSalva.getDataInicio().isAfter(LocalDateTime.now()) && pautaSalva.getDataFim().isBefore(LocalDateTime.now())) {
                    if (assembleiaService.validarCPF(cpf)) {
                        Associado associado = associadoService.obterPorCpf(cpf).get();
                        assembleiaSalva.setVotos(Sets.newHashSet(votoService.salvar(new Voto(associado, opcao))));
                        assembleiaService.salvar(assembleiaSalva);
                    }else{
                        return new ResponseEntity<>("CPF INVÁLIDO", HttpStatus.BAD_REQUEST);
                    }
                } else {
                    return new ResponseEntity<>("SESSÃO FECHADA", HttpStatus.BAD_REQUEST);
                }
            }else{
                return new ResponseEntity<>("ASSEMBLEIA NÃO ENCONTRADA", HttpStatus.NOT_FOUND);
            }
            return null;
        }catch (Exception e) {
            log.error("[AssembleiaController] [votar] [{}]",  idAssembleia, e);
            return handleException(e);
        }

    }

    @PostMapping("publicarResultado/{idAssembleia}")
    public ResponseEntity<String> publicarResultado(@RequestParam(required = true)  Long idAssembleia) {
        Optional<Assembleia> assembleiaSalva = assembleiaService.obterPorId(idAssembleia);
        if (assembleiaSalva.isPresent()) {
            if (assembleiaSalva.get().getPauta().getDataFim().isBefore(LocalDateTime.now())) {
                assembleiaService.publicarResultado(assembleiaSalva.get());
            } else {
                return new ResponseEntity<>("SESSÃO NÃO FINALIZADA", HttpStatus.NOT_ACCEPTABLE);
            }
        }else{
            return new ResponseEntity<>("ASSEMBLEIA NÃO ENCONTRADA", HttpStatus.NOT_FOUND);
        }
        return null;
    }
}
