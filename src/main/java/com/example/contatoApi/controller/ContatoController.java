package com.example.contatoApi.controller;

import com.example.contatoApi.model.Contato;
import com.example.contatoApi.repository.ContatoRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ContatoController {

    @Autowired
    private ContatoRepository contatoRepository;


    @ApiOperation(value = "View a list of available employees", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso"),
            @ApiResponse(code = 401, message = "erro 401"),
            @ApiResponse(code = 404, message = "erro 404")
    })
    @GetMapping("/{idContato}")
    public  Contato getAll(@PathVariable(value = "idContato") String id) {
        return (Contato) contatoRepository.findById(id).get();
    }


    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "no content"),
            @ApiResponse(code = 400, message = "erro 400"),
            @ApiResponse(code = 401, message = "erro 401"),
            @ApiResponse(code = 404, message = "erro 404")
    })
    @ApiOperation(value = "Update contato")
    @PutMapping("/{idContato}")
    public ResponseEntity < Contato > update (
            @ApiParam(value = "update object", required = true) @PathVariable(value = "idContato") String id,
            @ApiParam(value = "Update  object", required = true) @Valid @RequestBody Contato details) {

        Contato contato = contatoRepository.findById(id).get();
        contato.setNome (details.getNome());
        contato.setCanal(details.getCanal());
        contato.setValor(details.getValor());
        contato.setObs(details.getObs());
        final Contato contatoNovo = contatoRepository.save(contato);
        return ResponseEntity.ok(contatoNovo);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "no content"),
            @ApiResponse(code = 401, message = "erro 401"),
            @ApiResponse(code = 404, message = "erro 404")
    })
    @ApiOperation(value = "Delete")
    @DeleteMapping("/{idContato}")
    public ResponseEntity < String > deleteEmployee(
            @ApiParam(value = "id", required = true) @PathVariable(value = "idContato") String id)
      {
          Contato contato = contatoRepository.findById(id).get();
          contatoRepository.delete(contato);


          return   ResponseEntity.ok("sucesso");
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "sucesso"),
            @ApiResponse(code = 401, message = "erro 401"),
            @ApiResponse(code = 404, message = "erro 404")
    })
    @ApiOperation(value = "list")
    @GetMapping("/{page}/{size}")
    public ResponseEntity<List> getContatos(@Valid @PathVariable(value = "page") int page, @Valid @PathVariable(value = "size") int size) {

        List contatos = new ArrayList<>();
        contatoRepository.findAll().forEach(contatos::add);
        return  ResponseEntity.ok(contatos.subList(page,size)); //simulacao page, size

    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "sucesso"),
            @ApiResponse(code = 401, message = "erro 401"),
            @ApiResponse(code = 400, message = "erro 400")
    })
    @ApiOperation(value = "post")
    @PostMapping("/")
    public ResponseEntity<Contato> create(@Valid @RequestBody Contato contato) {
        return ResponseEntity.ok(contatoRepository.save(contato));
    }

}
