package br.com.level4.sicredi.service.impl;

import br.com.level4.sicredi.model.Assembleia;
import br.com.level4.sicredi.repository.AssembleiaRepository;
import br.com.level4.sicredi.service.AssembleiaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssembleiaServiceImpl implements AssembleiaService {

    private static final Logger log = LoggerFactory.getLogger(AssembleiaServiceImpl.class);

    private AssembleiaRepository assembleiaRepository;

    private JmsTemplate jmsTemplate;

    @Autowired
    public AssembleiaServiceImpl(AssembleiaRepository assembleiaRepository,
                                 JmsTemplate jmsTemplate) {
        this.assembleiaRepository = assembleiaRepository;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public Assembleia salvar(Assembleia assembleia) {
        return assembleiaRepository.save(assembleia);
    }

    @Override
    public Optional<Assembleia> obterPorId(Long id) {
        return assembleiaRepository.findById(id);
    }

    @Override
    public boolean validarCPF(String cpf) {
        try {
            HttpResponse<JsonNode> response = Unirest.get("https://user-info.herokuapp.com/users/{cpf}")
                    .routeParam("cpf", cpf)
                    .asJson();
            return !new Integer(response.getStatus()).equals(HttpStatus.SC_NOT_FOUND);
        } catch (UnirestException error) {
            log.error("[AssembleiaServiceImpl] [validarCPF]", error);
        }
        return false;
    }

    @Override
    public void publicarResultado(Assembleia assembleia) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            jmsTemplate.convertAndSend("queue.votacao", mapper.writeValueAsString(assembleia));
        } catch (JsonProcessingException error) {
            log.error("[AssembleiaServiceImpl] [publicarResultado]", error);
        }
    }
}
