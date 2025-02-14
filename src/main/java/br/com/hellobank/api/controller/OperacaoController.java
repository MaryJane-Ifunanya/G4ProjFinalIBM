package br.com.hellobank.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.hellobank.api.model.entity.Transferencia;
import br.com.hellobank.api.model.request.SaqueRequest;
import br.com.hellobank.api.model.request.DepositoRequest;
import br.com.hellobank.api.model.request.TransferenciaRequest;
import br.com.hellobank.api.model.response.SaqueResponse;
import br.com.hellobank.api.model.response.DepositoResponse;
import br.com.hellobank.api.model.response.TransferenciaResponse;
import br.com.hellobank.api.service.interfaceServ.IOperacaoService;
import br.com.hellobank.api.service.interfaceServ.OperacaoService;

@RestController
@RequestMapping(path = "/operacao")
public class OperacaoController {

    @Autowired
    private IOperacaoService operacaoService;

    @GetMapping(value = "/saldo/{contaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> balance(@PathVariable("contaId") Integer contaId) {
        String saldo = operacaoService.getSaldo(Long.valueOf(contaId));
        return new ResponseEntity<>(saldo, HttpStatus.OK);
    }

    @PostMapping(value = "/deposito", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deposito(@RequestBody DepositoRequest depositoRequest) {
        DepositoResponse depositoResponse = operacaoService.deposito(depositoRequest);
        return new ResponseEntity<>(depositoResponse, HttpStatus.OK);
    }

    @PostMapping( value = "/saque", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity cashOut(@RequestBody SaqueRequest saqueRequest) {
        SaqueResponse saqueResponse = operacaoService.saque(saqueRequest);
        return new ResponseEntity<>(saqueResponse, HttpStatus.OK);
    }

    @PostMapping( value = "/transferencia", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity cashOut(@RequestBody TransferenciaRequest transferenciaRequest) {
        TransferenciaResponse transferenciaResponse = operacaoService.transferencia(transferenciaRequest);
        return new ResponseEntity<>(transferenciaResponse, HttpStatus.OK);
    }

    @GetMapping( value = "/contaTransacoes/{contaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity contaTransacoes(@PathVariable("contaId") Integer contaId) {
        List<Transferencia> transferencias = operacaoService.getTransferencias(Long.valueOf(contaId));
        return new ResponseEntity<>(transferencias, HttpStatus.OK);
    }
}